package net.bodz.bas.javadoc;

import java.util.List;

public interface Javadoc {

    /**
     * Get the body contents.
     * 
     * @return Non-<code>null</code> text with leading/trailing space trimmed.
     */
    String getText();

    /**
     * Get a section of the body contents.
     * <p>
     * The body contents of Javadoc maybe separated by &lt;a name="sectionName"&gt; to several
     * sections. Using this method to get a specified section.
     * 
     * @param sectionName
     *            The name of the section. <code>null</code> refers to the unnamed section.
     * @return The section text, <code>null</code> if the section isn't existed.
     */
    String getTextSection(String sectionName);

    /**
     * List of annotated text.
     * <p>
     * There maybe multiple annotations with the same name.
     * 
     * @param annotationName
     *            The name of the annotation. For example, <code>"see"</code> for @see,
     *            <code>"throws"</code> for @throws.
     * @return List of the annotation texts. <code>null</code> if the annotation doesn't exist.
     */
    List<?> getAnnotationList(String annotationName);

    /**
     * 
     */
    String getAnnotation(String annotationName);

    List<?> getSeeAlsos();

    /**
     * Same as getAnnotation("author").
     * 
     * @return The author name.
     */
    String getSince();

    /**
     * Same as getAnnotation("author").
     * 
     * @return The author name.
     */
    List<?> getAuthors();
}
