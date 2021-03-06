package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE_DATE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteWorksCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.schedule.Date;


/**
 * Parses input arguments and creates a new {@code DeleteWorksCommand} object
 */
public class DeleteWorksCommandParser implements Parser<DeleteWorksCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteWorksCommand
     * and returns an DeleteWorksCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteWorksCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SCHEDULE_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_SCHEDULE_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteWorksCommand.MESSAGE_USAGE));
        }

        Set<Date> dates = ParserUtil.parseDates(argMultimap.getAllValues(PREFIX_SCHEDULE_DATE));

        return new DeleteWorksCommand(dates);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
