package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.NoticeRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.NoticeWriteResponseDTO;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.entity.Notice;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.FileRepository;
import inhatc.yulo.back.board.repository.NoticeRepository;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeWriteService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final String uploadDir = "C:/path/to/upload";

    @Transactional
    public NoticeWriteResponseDTO writeNotice(NoticeRequestDTO noticeRequestDTO) throws IOException {

        User user = userRepository.findById(noticeRequestDTO.getUserId())
                .orElseThrow( () -> new IllegalArgumentException("User not found"));

        Notice notice = Notice.builder()
                .user(user)
                .title(noticeRequestDTO.getTitle())
                .content(noticeRequestDTO.getContent())
                .build();

        Notice savedNotice = noticeRepository.save(notice);

        List<File> files = new ArrayList<>();
        if (noticeRequestDTO.getFiles() != null && noticeRequestDTO.getFiles().length > 0) {
            for (MultipartFile file : noticeRequestDTO.getFiles()) {
                if (!file.isEmpty()) {
                    String origFilename = file.getOriginalFilename();
                    String fileName = System.currentTimeMillis() + "_" + origFilename;
                    Path filePath = Paths.get(uploadDir, fileName);
                    if (!Files.exists(filePath.getParent())) {
                        Files.createDirectories(filePath.getParent());
                    }
                    Files.write(filePath, file.getBytes());

                    File fileEntity = File.builder()
                            .origFilename(origFilename)
                            .fileName(fileName)
                            .filePath(filePath.toString())
                            .notice(savedNotice)
                            .build();
                    fileRepository.save(fileEntity);
                    files.add(fileEntity);
                }
            }
        }

        return NoticeWriteResponseDTO.builder()
                .userName(user.getUserName())
                .title(savedNotice.getTitle())
                .content(savedNotice.getContent())
                .createDate(savedNotice.getCreateDate())
                .files(files)
                .build();

    }
}
