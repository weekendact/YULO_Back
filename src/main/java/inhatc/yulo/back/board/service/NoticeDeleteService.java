package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.NoticeDeleteRequestDTO;
import inhatc.yulo.back.board.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeDeleteService {

    private final NoticeRepository noticeRepository;

    public boolean deleteNotice(NoticeDeleteRequestDTO noticeDeleteRequestDTO) {
        if(noticeRepository.existsById(noticeDeleteRequestDTO.getNoticeId())) {
            noticeRepository.deleteById(noticeDeleteRequestDTO.getNoticeId());
            return true;
        } else {
            return false;
        }
    }
}
