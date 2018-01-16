package com.korobko.commands;

/**
 * @author Vova Korobko
 */
public enum CommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SHOW_SIGNUP_VIEW(new ShowSignUpViewCommand()),
    REGISTER(new RegisterCommand()),
    PROFILE(new ProfileCommand()),
    UPDATE_PROFILE(new UpdateProfileCommand()),
    ADMIN_BUSES(new AdminBusesCommand()),
    ADMIN_DRIVERS(new AdminDriversCommand()),
    APPOINT_ROUTE(new AppointRouteCommand()),
    UPDATE_BUS_ROUTE(new UpdateBusRouteCommand()),
    APPOINT_BUS(new AppointBusCommand()),
    UPDATE_DRIVER_BUS(new UpdateDriverBusCommand()),
    SHOW_APPOINTMENT(new ShowAppointmentCommand()),
    APPROVE_APPOINTMENT(new ApproveAppointmentCommand()),
    SHOW_EMPLOYEES(new ShowEmployeesCommand()),
    SHOW_ROUTES(new ShowRoutesCommand()),
    SHOW_BUS_MODELS(new ShowBusModelsCommand()),
    SHOW_BUSES(new ShowBusesCommand()),
    CREATE_ROUTE(new CreateRouteCommand()),
    UPDATE_ROUTE(new UpdateRouteCommand()),
    UPDATE_POSITION(new UpdatePositionCommand()),
    CREATE_BUS_MODEL(new CreateBusModelCommand()),
    UPDATE_BUS_MODEL(new UpdateBusModelCommand()),
    CREATE_BUS(new CreateBusCommand()),
    UPDATE_BUS(new UpdateBusCommand()),
    EMPTY(new EmptyCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    Command getCurrentCommand() {
        return command;
    }
}

