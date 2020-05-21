package View;

public interface View {

    default void showMessage(String message) {
        System.out.println("Message::" + message);
    }

    default View getView(TypeView view) {
        if(view == TypeView.WINDOWVIEW) {
            return new WindowView();
        }
        return new WindowView();
    }
}
