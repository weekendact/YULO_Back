package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.BoardWriteRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.BoardWriteResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardWriteService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardWriteResponseDTO writeBoard(BoardWriteRequestDTO boardWriteRequestDTO) {

        User user = userRepository.findById(boardWriteRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Board board = Board.builder()
                .user(user)
                .title(boardWriteRequestDTO.getTitle())
                .content(boardWriteRequestDTO.getContent())
                .build();

        Board saveBoard = boardRepository.save(board);

        return BoardWriteResponseDTO.builder()
                .userName(user.getUserName())
                .title(saveBoard.getTitle())
                .content(saveBoard.getContent())
                .createDate(saveBoard.getCreateDate())
                .build();
    }
}
