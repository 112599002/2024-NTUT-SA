package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.home.Home;
import tw.teddysoft.clean.domain.model.user.event.UserRegistrationSucceeded;
import tw.teddysoft.clean.domain.usecase.repository.Repository;


public class UserEventHandler {

    private Repository<Home> homeRepository;
    private DomainEventBus eventBus;

    public UserEventHandler(Repository homeRepository, DomainEventBus eventBus){
        this.homeRepository = homeRepository;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void handleEvent(UserRegistrationSucceeded domainEvent) {
        System.out.println("UserEventHandler, event = " + domainEvent.detail());

        Home home = new Home(domainEvent.getSourceName(), domainEvent.getSourceId());
        homeRepository.save(home);
        eventBus.postAll(home);
    }
}
