package com.monkeyhorse.blog.support.web;

import org.springframework.stereotype.Service;
import org.python.util.PythonInterpreter;

/**
 *  @author uoor<i@monkeyhorse.cn thx raysmood>
 */
@Service
public class PygmentsService implements SyntaxHighlightService {

    @Override
    public String highlight(String content) {
        PythonInterpreter interpreter = new PythonInterpreter();

        // Set a variable with the content you want to work with
        interpreter.set("code", content);

        // Simple use Pygments as you would in Python
        interpreter.exec("from pygments import highlight\n"
            + "from pygments.lexers import PythonLexer\n"
            + "from pygments.formatters import HtmlFormatter\n"
            + "\nresult = highlight(code, PythonLexer(), HtmlFormatter())");

        return interpreter.get("result", String.class);
    }
}
