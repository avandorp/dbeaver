/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2022 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.parser.common.grammar;

import java.util.regex.Pattern;

/**
 * Represents terminal part of the text with regular pattern
 */
public class RegexExpression extends TerminalExpression {
    
    // see https://docs.oracle.com/javase/tutorial/essential/regex/literals.html
    private static final Pattern _regexEscapeCharsPattern = Pattern.compile("([\\\\\\.\\?\\!\\[\\]\\{\\}\\(\\)\\<\\>\\*\\+\\-\\=\\^\\$\\|])");

    public RegexExpression(String pattern) {
        super(pattern);
    }

    @Override
    protected <T, R> R applyImpl(ExpressionVisitor<T, R> visitor, T arg) {
        return visitor.visitRegex(this, arg);
    }

    public static String escapeSpecialChars(String str) {
        return  str == null || str.length() == 0 ? "" : _regexEscapeCharsPattern.matcher(str).replaceAll("\\\\$0");
    }
}