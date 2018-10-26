package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelTypes;

import java.util.Set;

/**
 * Reverts the {@code model}'s address book, schedule list, expenses list, recruitment list
 * to its previously undone state.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoModel()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        Set<ModelTypes> myModelRedoSet  = model.getNextCommitType();

        for (ModelTypes myModel : myModelRedoSet) {

            switch (myModel) {
                case ADDRESS_BOOK:
                    if (!model.canRedoAddressBook()) {
                        throw new CommandException(MESSAGE_FAILURE);
                    }
                    model.redoAddressBook();
                    model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                    break;

                case SCHEDULES_LIST:
                    if (!model.canRedoScheduleList()) {
                        throw new CommandException(MESSAGE_FAILURE);
                    }
                    model.redoScheduleList();
                    model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
                    break;

                default:
                    break;
            }
        }
        model.redoModelList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
