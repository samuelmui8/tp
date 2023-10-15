package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;
import seedu.address.model.item.review.ItemReview;
import seedu.address.model.stall.Stall;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Stall> PREDICATE_SHOW_ALL_STALLS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a stall with the same identity as {@code stall} exists in the address book.
     */
    boolean hasStall(Stall stall);

    /**
     * Deletes the given stall.
     * The stall must exist in the address book.
     */
    void deleteStall(Stall target);

    /**
     * Adds the given stall.
     * {@code stall} must not already exist in the address book.
     */
    void addStall(Stall stall);

    /**
     * Replaces the given stall {@code target} with {@code editedStall}.
     * {@code target} must exist in the address book.
     * The stall identity of {@code editedStall} must not be the same as another existing stall in the address book.
     */
    void setStall(Stall target, Stall editedStall);

    /** Returns an unmodifiable view of the filtered stall list */
    ObservableList<Stall> getFilteredStallList();

    /**
     * Updates the filter of the filtered stall list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStallList(Predicate<Stall> predicate);

    /**
     * Returns true if an item with the same identity as {@code item} exists in the menu.
     */
    boolean hasItem(Stall stallIndex, Item item);

    boolean hasItemReview(Stall stallIndex, Item item);

    /**
     * Adds the given item.
     * {@code item} must not already exist in the menu.
     */
    void addItem(Stall stallIndex, Item item);

    /**
     * Deletes the given item.
     * The item must exist in the address book.
     */
    void deleteItem(Stall stallIndex, Item item);

    /**
     * Adds the given item review.
     *
     * {@code itemReview} must not already exist in the item.
     */
    void addItemReview(Stall stallIndex, Item itemIndex, ItemReview itemReview);

    /**
     * Deletes the given item review.
     *
     * {@code itemReview} must exist in the item.
     */
    void deleteItemReview(Stall stallIndex, Item itemIndex);
}
