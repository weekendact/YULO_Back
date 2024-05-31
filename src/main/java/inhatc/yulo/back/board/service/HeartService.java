package inhatc.yulo.back.board.service;

import com.sun.jdi.request.DuplicateRequestException;
import inhatc.yulo.back.board.dto.requestdto.HeartRequestDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.entity.Heart;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.HeartRepository;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public int addHeart(HeartRequestDTO heartRequestDTO) throws Exception {

        User user = userRepository.findByUserId(heartRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Could not found member id : " + heartRequestDTO.getUserId()));

        Board board = boardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Could not found board id " + heartRequestDTO.getBoardId()));

        // 이미 좋아요가 되어 있으면 에러
        if (heartRepository.findByUserAndBoard(user, board).isPresent()) {
            throw new DuplicateRequestException("already exist data by user id : " + user.getUserId() + ", " + "board id : " + board.getId());
        }

        Heart heart = Heart.builder()
                .board(board)
                .user(user)
                .build();

        heartRepository.save(heart);
        boardRepository.incrementLikeCount(board.getId());
        return board.getHeartCount() + 1; // 업데이트된 좋아요 수 반환
    }

    @Transactional
    public int removeHeart(HeartRequestDTO heartRequestDTO) throws Exception {

        User user = userRepository.findByUserId(heartRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Could not found user id : " + heartRequestDTO.getUserId()));

        Board board = boardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Could not found board id : " + heartRequestDTO.getBoardId()));

        Heart heart = heartRepository.findByUserAndBoard(user, board)
                .orElseThrow(() -> new IllegalArgumentException("Could not found heart"));

        heartRepository.delete(heart);
        boardRepository.decrementLikeCount(board.getId());
        return board.getHeartCount() - 1; // 업데이트 된 좋아요 수 반환
    }
}
