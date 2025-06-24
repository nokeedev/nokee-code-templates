package dev.nokee.platform.jni.fixtures.elements;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class JavaUtils {
	public static UnaryOperator<String> replace(String regex, String value) {
		return content -> {
			return Pattern.compile(regex, Pattern.MULTILINE | Pattern.DOTALL).matcher(content).replaceAll(result -> {
				return result.group().substring(0, result.start(1) - result.start()) + value + result.group().substring(result.end(1) - result.start());
			});
		};
	}
}
