package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.NoticeDeleteRequestDTO;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.repository.FileRepository;
import inhatc.yulo.back.board.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeDeleteService {

    private final NoticeRepository noticeRepository;
    private final FileRepository fileRepository;
    private final String uploadDir = "C:/path/to/upload";

    @Transactional
    public boolean deleteNotice(NoticeDeleteRequestDTO noticeDeleteRequestDTO) {
        Long noticeId = noticeDeleteRequestDTO.getNoticeId();

        if (noticeRepository.existsById(noticeId)) {
            List<File> files = fileRepository.findByNoticeId(noticeId);

            // 파일 시스템에서 파일 삭제
            for (File file : files) {
                Path filePath = Paths.get(file.getFilePath());
                try {
                    Files.deleteIfExists(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            // DB에서 파일 삭제
            fileRepository.deleteAll(files);

            // 공지사항 삭제
            noticeRepository.deleteById(noticeId);
            return true;
        } else {
            return false;
        }
    }
}
