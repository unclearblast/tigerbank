package ru.tigrbank.finance.command;

import ru.tigrbank.finance.facade.BankAccountFacade;

public class CreateAccountCommand implements Command {

    private final BankAccountFacade facade;
    private final String name;

    public CreateAccountCommand(
            BankAccountFacade facade,
            String name) {

        this.facade = facade;
        this.name = name;
    }

    @Override
    public void execute() {

        facade.createAccount(name);
    }
}
