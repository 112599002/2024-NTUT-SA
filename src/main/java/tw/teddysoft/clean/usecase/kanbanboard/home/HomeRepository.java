package tw.teddysoft.clean.usecase.kanbanboard.home;

import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.usecase.repository.Repository;
import tw.teddysoft.clean.domain.usecase.repository.RepositoryPeer;

public class HomeRepository extends Repository<Home> {

    public HomeRepository(RepositoryPeer peer) {
        super(peer);
    }
}