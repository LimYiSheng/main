package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Schedule's year in the schedule List.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class Year {
    public static final String MESSAGE_YEAR_CONSTRAINTS =
            "Year should only be integers between 2000 to 2099";
    public static final String YEAR_VALIDATION_REGEX = "^(20)\\d\\d$";
    public final String value;

    /**
     * Constructs a {@code year}.
     *
     * @param year A valid year.
     */

    public Year (String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_YEAR_CONSTRAINTS);
        value = year;
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidYear(String test) {
        return test.matches(YEAR_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Year // instanceof handles nulls
                && value.equals(((Year) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
