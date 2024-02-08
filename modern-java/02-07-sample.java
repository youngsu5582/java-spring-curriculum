
import java.nio.file.Files;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final var traders = getTraders();
        final var transactions = getTransactions(traders);

        //2011년에 이루어진 모든 거래를 찾아 값에 따라 정렬하세요(낮은 것부터 높은 것까지).
        Predicate<Transaction> isYearIs2011 = (transaction) -> transaction.getYear() == 2011;
        transactions
                .stream()
                .filter(isYearIs2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);


        //트레이더들이 일하는 모든 고유한 도시는 어디인가요?
        traders
                .stream()
                .map(Trader::getCity)
                .distinct();

        //케임브리지 출신의 모든 트레이더를 찾아 이름순으로 정렬하세요.
        traders
                .stream()
                .filter(trader -> trader
                        .getCity()
                        .equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();

        //알파벳 순으로 정렬된 모든 트레이더들의 이름으로 구성된 문자열을 반환하세요. - ??
        traders.stream()
               .map(Trader::getName)
               .sorted()
               .collect(Collectors.joining());

        //밀라노에 기반을 둔 트레이더가 있나요?
        traders
                .stream()
                .filter(trader -> trader
                        .getCity()
                        .equals("Milan"))
                .findAny()
                .orElseThrow();

        //케임브리지에 사는 트레이더들의 모든 거래 값들을 출력하세요.

        Predicate<Trader> isTraderInCambridge = trader -> trader.getCity()
                                                                .equals("Cambridge");
        List<Trader> tradersInCambridge = getTraders()
                .stream()
                .filter(isTraderInCambridge)
                .toList();
        transactions
                .stream()
                .filter(transaction -> tradersInCambridge.contains(transaction.getTrader()))
                .map(Transaction::getValue)
                .toList();

        // 모든 거래 중 가장 높은 값은 얼마인가요?
        transactions
                .stream()
                .map(Transaction::getValue)
                .max(Comparator.naturalOrder());


        //가장 작은 값의 거래를 찾으세요.

        Integer minValue = transactions
                .stream()
                .map(Transaction::getValue)
                .min(Integer::min)
                .orElse(0);
        transactions
                .stream()
                .filter(transaction -> transaction.getValue() == minValue)
                .findAny()
                .get();
    }

    private static List<Trader> getTraders() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        return List.of(raoul, mario, alan, brian);
    }

    private static List<Transaction> getTransactions(List<Trader> traders) {
        List<Transaction> transactions = Arrays.asList(new Transaction(getTraderByName(traders, "Brian"), 2011, 300),
                new Transaction(getTraderByName(traders, "Raoul"), 2012, 1000),
                new Transaction(getTraderByName(traders, "Raoul"), 2011, 400),
                new Transaction(getTraderByName(traders, "Mario"), 2012, 710),
                new Transaction(getTraderByName(traders, "Mario"), 2012, 700),
                new Transaction(getTraderByName(traders, "Alan"), 2012, 950)
        );
        return transactions;
    }

    private static Trader getTraderByName(List<Trader> traders, String name) {
        return traders
                .stream()
                .filter(trader -> trader
                        .getName()
                        .equals(name))
                .findFirst()
                .orElseThrow();
    }

public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " + "value:" + this.value + "}";
    }
}

}
