package dk.via.chatpat.viewmodel;

import dk.via.chatpat.model.ChatModel;

public class ViewModelFactory {
    private final ChatViewModel chatViewModel;
    private final LoginViewModel loginViewModel;

    public ViewModelFactory(ChatModel chatModel) {
        this.chatViewModel = new ChatViewModel(chatModel);
        this.loginViewModel = new LoginViewModel(chatModel);
    }
    public ChatViewModel getChatViewModel() {
        return chatViewModel;
    }
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}
