package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.NoticeUpdateRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.NoticeUpdateResponseDTO;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.entity.Notice;
import inhatc.yulo.back.board.repository.FileRepository;
import inhatc.yulo.back.board.repository.NoticeRepository;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeUpdateService {

    private final NoticeRepository noticeRepository;
    private final FileRepository fileRepository;
    private final String uploadDir = "C:/path/to/upload";

    @Transactional
    public NoticeUpdateResponseDTO updateNotice(NoticeUpdateRequestDTO noticeUpdateRequestDTO) throws IOException {
        Notice notice = noticeRepository.findById(noticeUpdateRequestDTO.getNoticeId())
                .orElseThrow(() -> new IllegalArgumentException("Notice not found"));

        notice.setTitle(noticeUpdateRequestDTO.getTitle());
        notice.setContent(noticeUpdateRequestDTO.getContent());

        Notice updatedNotice = noticeRepository.save(notice);

        // 삭제할 파일 처리
        if (noticeUpdateRequestDTO.getDeleteFileId() != null) {
            for (Long fileId : noticeUpdateRequestDTO.getDeleteFileId()) {
                File file = fileRepository.findById(fileId)
                        .orElseThrow(() -> new IllegalArgumentException("File not found"));
                fileRepository.delete(file);
                // 파일 시스템에서 파일 삭제
                Path filePath = Paths.get(file.getFilePath());
                Files.deleteIfExists(filePath);
            }
        }

        // 새로 추가할 파일 처리
        if (noticeUpdateRequestDTO.getNewFiles() != null) {
            for (MultipartFile newFile : noticeUpdateRequestDTO.getNewFiles()) {
                if (!newFile.isEmpty()) {
                    String origFilename = newFile.getOriginalFilename();
                    String fileName = System.currentTimeMillis() + "_" + origFilename;
                    Path filePath = Paths.get(uploadDir, fileName);

                    // 디렉터리 생성
                    if (!Files.exists(filePath.getParent())) {
                        Files.createDirectories(filePath.getParent());
                    }
                    Files.write(filePath, newFile.getBytes());

                    File fileEntity = File.builder()
                            .origFilename(origFilename)
                            .fileName(fileName)
                            .filePath(filePath.toString())
                            .notice(updatedNotice)
                            .build();
                    fileRepository.save(fileEntity);
                }
            }
        }
        List<File> files = fileRepository.findByNoticeId(updatedNotice.getId());


        return NoticeUpdateResponseDTO.builder()
                .userName(notice.getUser().getUserName())
                .title(updatedNotice.getTitle())
                .content(updatedNotice.getContent())
                .createDate(updatedNotice.getCreateDate())
                .updateDate(updatedNotice.getModifiedDate())
                .files(files)
                .build();
    }
}
