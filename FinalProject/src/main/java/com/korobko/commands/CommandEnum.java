package com.korobko.commands;

/**
 * @author Vova Korobko
 */
public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SHOW_SIGNUP_VIEW {
        {
            this.command = new ShowSignUpViewCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    UPDATE_PROFILE {
        {
            this.command = new UpdateProfileCommand();
        }
    },
    ADMIN_BUSES {
        {
            this.command = new AdminBusesCommand();
        }
    },
    ADMIN_DRIVERS {
        {
            this.command = new AdminDriversCommand();
        }
    },
    APPOINT_ROUTE {
        {
            this.command = new AppointRouteCommand();
        }
    },
    UPDATE_BUS_ROUTE {
        {
            this.command = new UpdateBusRouteCommand();
        }
    },
    APPOINT_BUS {
        {
            this.command = new AppointBusCommand();
        }
    },
    UPDATE_DRIVER_BUS {
        {
            this.command = new UpdateDriverBusCommand();
        }
    },
    SHOW_APPOINTMENT {
        {
            this.command = new ShowAppointmentCommand();
        }
    },
    APPROVE_APPOINTMENT {
        {
            this.command = new ApproveAppointmentCommand();
        }
    },
    SHOW_EMPLOYEES {
        {
            this.command = new ShowEmployeesCommand();
        }
    },
    SHOW_ROUTES {
        {
            this.command = new ShowRoutesCommand();
        }
    },
    SHOW_BUS_MODELS {
        {
            this.command = new ShowBusModelsCommand();
        }
    },
    SHOW_BUSES {
        {
            this.command = new ShowBusesCommand();
        }
    },
    CREATE_ROUTE {
        {
            this.command = new CreateRouteCommand();
        }
    },
    UPDATE_ROUTE {
        {
            this.command = new UpdateRouteCommand();
        }
    },
    UPDATE_POSITION {
        {
            this.command = new UpdatePositionCommand();
        }
    },
    CREATE_BUS_MODEL {
        {
            this.command = new CreateBusModelCommand();
        }
    },
    UPDATE_BUS_MODEL {
        {
            this.command = new UpdateBusModelCommand();
        }
    },
    CREATE_BUS {
        {
            this.command = new CreateBusCommand();
        }
    },
    UPDATE_BUS {
        {
            this.command = new UpdateBusCommand();
        }
    },
    EMPTY {
        {
            this.command = new EmptyCommand();
        }
    };
    Command command;
    Command getCurrentCommand() {
        return command;
    }
}

