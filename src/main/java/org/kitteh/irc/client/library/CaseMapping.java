/*
 * * Copyright (C) 2013-2015 Matt Baxter http://kitteh.org
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kitteh.irc.client.library;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * ISUPPORT CASEMAPPING.
 */
public enum CaseMapping {
    /**
     * A-Z become a-z
     */
    ASCII('Z'),
    /**
     * A-Z become a-z, [\]^ become {\}~
     */
    RFC1459(']'),
    /**
     * A-Z become a-z, [\] become {\}
     */
    STRICT_RFC1459('^');

    private static final Map<String, CaseMapping> nameMap = new HashMap<>();

    static {
        for (CaseMapping caseMapping : values()) {
            nameMap.put(caseMapping.name().replace('_', '-'), caseMapping);
        }
    }

    /**
     * Gets a CaseMapping by name. Case insensitive.
     *
     * @param name the name of the CaseMapping to get
     * @return the matching CaseMapping if one exists
     */
    @Nonnull
    public static Optional<CaseMapping> getByName(@Nullable String name) {
        return (name == null) ? Optional.empty() : Optional.of(nameMap.get(name.toUpperCase()));
    }

    private final char upperbound;

    CaseMapping(char upperbound) {
        this.upperbound = upperbound;
    }

    /**
     * Converts a given String to lowercase per spec.
     *
     * @param input string to be lowercased
     * @return lowercased string
     * @throws IllegalArgumentException if input is null
     */
    @Nonnull
    public String toLowerCase(@Nonnull String input) {
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if ((c >= 'A') && (c <= this.upperbound)) {
                arr[i] += (char) 32;
            }
        }
        return new String(arr);
    }
}