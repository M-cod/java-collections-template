package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getWords(text).stream().distinct().count();
    }

    @Override
    public List<String> getWords(String text) {
        Stream<String> stringStream = Arrays.stream(text.split("\\W+"));
        return stringStream.collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        ArrayList<String> list = new ArrayList<>(getWords(text));
        return list.stream().collect(Collectors.toMap(Function.identity(), x -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if (direction.equals(Direction.DESC)){
            return getWords(text).stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
        }
        return getWords(text).stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
    }
}
