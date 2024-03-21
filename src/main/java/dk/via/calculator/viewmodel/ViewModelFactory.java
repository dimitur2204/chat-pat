package dk.via.calculator.viewmodel;

import dk.via.calculator.model.Model;

public class ViewModelFactory {
    private final CalculatorViewModel convertViewModel;
    private final ChatListViewModel chatListViewModel;

    public ViewModelFactory(Model model) {
        this.convertViewModel = new CalculatorViewModel(model);
        this.chatListViewModel = new ChatListViewModel(model);
    }


    public CalculatorViewModel getConvertViewModel() {
        return convertViewModel;
    }
    public ChatListViewModel getChatListViewModel() {
        return chatListViewModel;
    }
}
