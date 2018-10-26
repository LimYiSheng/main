package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelTypes;

import java.util.Iterator;
import java.util.Set;

/**
 * Reverts the {@code model}'s address book, schedule list, expenses list, recruitment list
 * to its previously undone state.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoModel()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        Set<ModelTypes> myModelUndoSet  = model.getLastCommitType();

        for (ModelTypes myModel : myModelUndoSet) {

            switch(myModel) {
                case SCHEDULES_LIST:
                    if (!model.canUndoScheduleList()) {
                        throw new CommandException(MESSAGE_FAILURE);
                    }
                    model.undoScheduleList();
                    model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
                    break;

                case ADDRESS_BOOK:
                    if (!model.canUndoAddressBook()) {
                        throw new CommandException(MESSAGE_FAILURE);
                    }
                    model.undoAddressBook();
                    model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                    break;

                default:
                    break;
            }
        }

        model.undoModelList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
