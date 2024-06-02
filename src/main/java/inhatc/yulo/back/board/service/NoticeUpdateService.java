package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.NoticeUpdateRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.NoticeUpdateResponseDTO;
import inhatc.yulo.back.board.entity.Notice;
import inhatc.yulo.back.board.repository.NoticeRepository;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeUpdateService {

    private final NoticeRepository noticeRepository;

    public NoticeUpdateResponseDTO updateNotice(NoticeUpdateRequestDTO noticeUpdateRequestDTO) {
        Notice notice = noticeRepository.findById(noticeUpdateRequestDTO.getNoticeId())
                .orElseThrow(() -> new IllegalArgumentException("Notice not found"));

        notice.setTitle(noticeUpdateRequestDTO.getTitle());
        notice.setContent(noticeUpdateRequestDTO.getContent());

        Notice updatedNotice = noticeRepository.save(notice);

        return NoticeUpdateResponseDTO.builder()
                .userName(notice.getUser().getUserName())
                .title(updatedNotice.getTitle())
                .content(updatedNotice.getContent())
                .createDate(updatedNotice.getCreateDate())
                .updateDate(updatedNotice.getModifiedDate())
                .build();
    }
}
