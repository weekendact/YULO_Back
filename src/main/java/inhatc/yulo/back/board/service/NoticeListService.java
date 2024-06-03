package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.responsedto.NoticeListResponseDTO;
import inhatc.yulo.back.board.entity.Notice;
import inhatc.yulo.back.board.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // DB에게 읽기 전용이라는 것을 알려줌
public class NoticeListService {

    private final NoticeRepository noticeRepository;

    public Page<NoticeListResponseDTO> getNoticeList(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page -1, pageSize); // 페이지 번호는 0부터 시작

        Page<Notice> noticePage = noticeRepository.findAll(pageable);
        List<NoticeListResponseDTO> responseDTOList = new ArrayList<>();

        for (Notice notice : noticePage.getContent()) {
            NoticeListResponseDTO responseDTO = NoticeListResponseDTO.builder()
                    .noticeId(notice.getId())
                    .title(notice.getContent())
                    .userName(notice.getUser().getUserName())
                    .build();
            responseDTOList.add(responseDTO);
        }

        return new PageImpl<>(responseDTOList, pageable, noticePage.getTotalElements());
    }
}
