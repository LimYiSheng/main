package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BONUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYEEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_EXPENSES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINIMUM_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MISCELLANEOUS_EXPENSES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRAVEL_EXPENSES;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddExpensesCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditRecruitmentPostCommand;
import seedu.address.logic.commands.ModifyAllPayCommand;
import seedu.address.logic.commands.ModifyPayCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_EMPLOYEEID + person.getEmployeeId().value + " ");
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_DATEOFBIRTH + person.getDateOfBirth().value + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_DEPARTMENT + person.getDepartment().value + " ");
        sb.append(PREFIX_POSITION + person.getPosition().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_SALARY + person.getSalary().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getDepartment().ifPresent(department -> sb.append(PREFIX_DEPARTMENT).append(department.value)
                .append(" "));
        descriptor.getPosition().ifPresent(position -> sb.append(PREFIX_POSITION).append(position.value)
                .append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code ModSalaryDescriptor}'s details.
     */
    public static String getModSalaryDescriptorDetails(ModifyPayCommand.ModSalaryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.value).append(" "));
        descriptor.getBonus().ifPresent(bonus -> sb.append(PREFIX_BONUS).append(bonus.value).append(" "));

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code ModAllSalaryDescriptor}'s details.
     */
    public static String getModAllSalaryDescriptorDetails(ModifyAllPayCommand.ModSalaryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.value).append(" "));
        descriptor.getBonus().ifPresent(bonus -> sb.append(PREFIX_BONUS).append(bonus.value).append(" "));

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExpensesDescriptor}'s details.
     */
    public static String getEditExpensesDescriptorDetails(AddExpensesCommand.EditExpensesDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getMedicalExpenses().ifPresent(medicalExpenses -> sb.append(PREFIX_MEDICAL_EXPENSES)
                .append(medicalExpenses.medicalExpenses).append(" "));
        descriptor.getTravelExpenses().ifPresent(travelExpenses -> sb.append(PREFIX_TRAVEL_EXPENSES)
                .append(travelExpenses.travelExpenses).append(" "));
        descriptor.getMiscellaneousExpenses().ifPresent(miscellaneousExpenses -> sb
                .append(PREFIX_MISCELLANEOUS_EXPENSES).append(miscellaneousExpenses.miscellaneousExpenses)
                .append(" "));

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPostDescriptor}'s details.
     */
    public static String getEditPostDescriptorDetails(EditRecruitmentPostCommand.EditPostDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPost().ifPresent(post -> sb.append(PREFIX_JOB_POSITION).append(post.value).append(" "));
        descriptor.getWorkExp().ifPresent(workExp -> sb.append(PREFIX_MINIMUM_EXPERIENCE).append(workExp.workExp)
                .append(" "));
        descriptor.getJobDescription().ifPresent(jobDescription -> sb.append(PREFIX_JOB_DESCRIPTION)
                .append(jobDescription.value).append(" "));

        return sb.toString();
    }
}
