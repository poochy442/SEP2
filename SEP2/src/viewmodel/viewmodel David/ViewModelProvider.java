package Sep2Project.viewmodel;

public class ViewModelProvider {
    private MainVM mainVM;


    public ViewModelProvider() {
        mainVM = new MainVM();

    }
    public MainVM getMainVM ()
    {
        return mainVM;
    }
}
