package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;
import seedu.address.model.stall.Stall;

/**
 * Deletes a item identified using it's displayed index from the address book.
 */
public class DeleteItemCommand extends Command {

    public static final String COMMAND_WORD = "delete-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed item list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " s/1 i/1";

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s from Stall: %2$s";

    private final Index itemIndex;
    private final Index stallIndex;

    /**
     * Creates a DeleteItemCommand to delete the specified {@code Item}
     */
    public DeleteItemCommand(Index stallIndex, Index itemIndex) {
        this.itemIndex = itemIndex;
        this.stallIndex = stallIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (stallIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Stall stallToDeleteFrom = lastShownList.get(stallIndex.getZeroBased());
        UniqueItemList menu = stallToDeleteFrom.getMenu();

        if (itemIndex.getZeroBased() >= menu.getSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToDelete = menu.getItem(itemIndex.getZeroBased());

        model.deleteItem(stallToDeleteFrom, itemToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, Messages.format(itemToDelete), Messages
                .format(stallToDeleteFrom)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteItemCommand)) {
            return false;
        }

        DeleteItemCommand otherDeleteItemCommand = (DeleteItemCommand) other;
        return itemIndex.equals(otherDeleteItemCommand.itemIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("itemIndex", itemIndex)
                .toString();
    }
}
