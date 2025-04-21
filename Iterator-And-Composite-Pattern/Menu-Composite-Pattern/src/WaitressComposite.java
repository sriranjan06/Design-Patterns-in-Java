public class WaitressComposite {
    MenuComponent allMenus;

    public WaitressComposite(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();
    }
}
