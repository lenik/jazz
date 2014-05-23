#!/bin/bash

for f in \
        document-metadata.html \
        edits.html \
        embedded-content-0.html \
        forms.html \
        grouping-content.html \
        interactive-elements.html \
        links.html \
        scripting-1.html \
        sections.html \
        semantics.html \
        tabular-data.html \
        text-level-semantics.html ; do
    wget http://www.w3.org/TR/html5/$f
done

