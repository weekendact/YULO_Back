package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.NoticeRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.NoticeWriteResponseDTO;
import inhatc.yulo.back.board.entity.Notice;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.NoticeRepository;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeWriteService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    public NoticeWriteResponseDTO writeNotice(NoticeRequestDTO noticeRequestDTO) {

        User user = userRepository.findById(noticeRequestDTO.getUserId())
                .orElseThrow( () -> new IllegalArgumentException("User not found"));

        Notice notice = Notice.builder()
                .user(user)
                .title(noticeRequestDTO.getTitle())
                .content(noticeRequestDTO.getContent())
                .build();

        Notice savedNotice = noticeRepository.save(notice);

        return NoticeWriteResponseDTO.builder()
                .userName(user.getUserName())
                .title(savedNotice.getTitle())
                .content(savedNotice.getContent())
                .createDate(savedNotice.getCreateDate())
                .build();

    }
}
