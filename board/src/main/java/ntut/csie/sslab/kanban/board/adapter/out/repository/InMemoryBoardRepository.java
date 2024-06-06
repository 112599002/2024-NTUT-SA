package ntut.csie.sslab.kanban.board.adapter.out.repository;

import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.workflow.entity.Workflow1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBoardRepository implements BoardRepository {
    private List<Board> stores;

    public InMemoryBoardRepository() {
        this.stores = new ArrayList<>();
    }

    @Override
    public Optional<Board> findById(String id) {
        return stores.stream().filter(board -> board.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Board board) {
        stores.add(board);
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not Available Yet. M3?");
    }
}
