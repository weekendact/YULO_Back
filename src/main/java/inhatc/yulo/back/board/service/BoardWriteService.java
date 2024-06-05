package inhatc.yulo.back.board.service;

import inhatc.yulo.back.board.dto.requestdto.BoardWriteRequestDTO;
import inhatc.yulo.back.board.dto.responsedto.BoardWriteResponseDTO;
import inhatc.yulo.back.board.entity.Board;
import inhatc.yulo.back.board.entity.File;
import inhatc.yulo.back.board.repository.BoardRepository;
import inhatc.yulo.back.board.repository.FileRepository;
import inhatc.yulo.back.user.entity.User;
import inhatc.yulo.back.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Service
public class BoardWriteService {

    private static final Logger logger = LoggerFactory.getLogger(BoardWriteService.class);

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final String uploadDir = "C:/path/to/upload";

    @Transactional
    public BoardWriteResponseDTO writeBoard(BoardWriteRequestDTO boardWriteRequestDTO, MultipartFile file) throws IOException {

        logger.debug("Starting writeBoard method");

        User user = userRepository.findById(boardWriteRequestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        logger.debug("User found: {}", user);

        Board board = Board.builder()
                .user(user)
                .title(boardWriteRequestDTO.getTitle())
                .content(boardWriteRequestDTO.getContent())
                .files(new ArrayList<>())
                .build();
        logger.debug("Board built: {}", board);

        Board savedBoard = boardRepository.save(board);
        logger.debug("Board saved: {}", savedBoard);

        if (file != null && !file.isEmpty()) {
            logger.debug("File received: {}", file.getOriginalFilename());
            String origFilename = file.getOriginalFilename();
            String fileName = System.currentTimeMillis() + "_" + origFilename;
            Path filePath = Paths.get(uploadDir, fileName);

            // 디렉터리 생성
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
                logger.debug("Created directories for path: {}", filePath.getParent());
            }

            Files.write(filePath, file.getBytes());
            logger.debug("File written to path: {}", filePath);

            File fileEntity = File.builder()
                    .origFilename(origFilename)
                    .fileName(fileName)
                    .filePath(filePath.toString())
                    .board(savedBoard)
                    .build();
            logger.debug("File entity built: {}", fileEntity);

            fileRepository.save(fileEntity);
            logger.debug("File entity saved: {}", fileEntity);

            savedBoard.getFiles().add(fileEntity); // board에 파일 추가
            logger.debug("File entity added to board: {}", savedBoard);
        } else {
            logger.debug("No file received or file is empty");
        }

            return BoardWriteResponseDTO.builder()
                    .userName(user.getUserName())
                    .title(savedBoard.getTitle())
                    .content(savedBoard.getContent())
                    .createDate(savedBoard.getCreateDate())
                    .files(savedBoard.getFiles()) // 파일 리스트 추가
                    .build();
        }
}
