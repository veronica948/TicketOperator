package by.bsu.first.command;

/**
 * Created by Пользователь on 07.10.2014.
 */
public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    SHOW_MOVIE_PAGE(new ShowMoviePageCommand()),
    REGISTRATION(new RegistrationCommand()),
    SEARCH(new SearchCommand()),
    SHOW_MOVIES(new ShowMoviesCommand()),
    GET_MOVIES(new GetMoviesCommand()),
    ADD_MOVIE(new AddMovieCommand()),
    DELETE_MOVIE(new DeleteMovieCommand()),
    CHANGE_MOVIE(new ChangeMovieCommand()),
    ADD_SEANCE(new AddSeanceCommand()),
    DELETE_SEANCE(new DeleteSeanceCommand()),
    GET_MOVIE(new GetMovieCommand()),
    SHOW_ALL_SEANCES(new ShowAllSeancesCommand()),
    SHOW_TICKETS(new ShowTicketsCommand()),
    SHOW_ORDER_PAGE(new ShowOrderPageCommand()),
    MAKE_ORDER(new MakeOrderCommand()),
    FIND_ORDER(new FindOrderCommand()),
    CHANGE_ORDER_STATUS(new ChangeOrderStatusCommand()),
    SHOW_CLIENT_ORDERS(new ShowClientOrdersCommand()),
    SHOW_ORDERS(new ShowOrdersCommand()),
    CHANGE_CLIENT_DATA(new ChangeClientDataCommand()),
    GET_CLIENT_DATA(new GetClientDataCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand());
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
