package dk.via.calculator.viewmodel;

import dk.via.calculator.model.Model;

public class ViewModelFactory {
    private final CalculatorViewModel convertViewModel;
    private final ChatListViewModel chatListViewModel;
    private final ChatViewModel chatViewModel;

    public ViewModelFactory(Model model) {
        this.convertViewModel = new CalculatorViewModel(model);
        this.chatListViewModel = new ChatListViewModel(model);
        this.chatViewModel = new ChatViewModel(model);
    }


    public CalculatorViewModel getConvertViewModel() {
        return convertViewModel;
    }
    public ChatListViewModel getChatListViewModel() {
        return chatListViewModel;
    }
    public ChatViewModel getChatViewModel() {
        return chatViewModel;
    }
}
