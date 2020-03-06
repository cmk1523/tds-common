package com.techdevsolutions.common.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.*;

public class AssociationTest {
    @Test
    public void test() throws JsonProcessingException {
        HashMap<String, Person> people= new HashMap<>();
        HashMap<String, Company> companies = new HashMap<>();

        Person satyaNadella = new Person("Satya Nadella");
        satyaNadella.getAliases().add("Satya Narayana Nadella");
        people.put(satyaNadella.getName(), satyaNadella);

        Person johnThompson = new Person("John Thompson");
        johnThompson.getAliases().add("John Wendell Thompson");
        people.put(johnThompson.getName(), johnThompson);

        Person bradSmith = new Person("Brad Smith");
        bradSmith.getAliases().add("Bradford Lee Smith");
        people.put(bradSmith.getName(), bradSmith);

        Person timCook = new Person("Tim Cook");
        timCook.getAliases().add("Timothy Donald Cook");
        people.put(timCook.getName(), timCook);

        Person arthurLevinson = new Person("Arthur Levinson");
        arthurLevinson.getAliases().add("Arthur D. Levinson");
        people.put(arthurLevinson.getName(), arthurLevinson);

        Person jeffWilliams = new Person("Jeff Williams");
        people.put(jeffWilliams.getName(), jeffWilliams);



        Company apple = new Company("Apple");
        companies.put(apple.getName(), apple);
        apple.getAliases().addAll(new ArrayList<>(Arrays.asList("Apple Inc", "Apple Incorporated", "Apple Inc.")));
        apple.getSymbols().add("AAPL");
        apple.setLeader(timCook);
        apple.getPeople().add(new PersonAssociation(timCook, "CEO",
                new Source("Wikipedia", new DateTime(2014, 2, 28, 0, 0, 0, 0))));
        apple.getPeople().add(new PersonAssociation(arthurLevinson, "Chairman",
                new Source("Wikipedia", new DateTime(2011, 11, 15, 0, 0, 0, 0))));
        apple.getPeople().add(new PersonAssociation(jeffWilliams, "COO",
                new Source("Wikipedia", new DateTime(2015, 12, 17, 0, 0, 0, 0))));

        Company microsoft = new Company("Microsoft");
        companies.put(microsoft.getName(), microsoft);
        microsoft.getAliases().addAll(new ArrayList<>(Arrays.asList("Microsoft Corporation", "Microsoft Corp", "Microsoft Corp.")));
        microsoft.getSymbols().add("MSFT");
        microsoft.setLeader(satyaNadella);
        microsoft.getPeople().add(new PersonAssociation(satyaNadella, "CEO",
                new Source("Wikipedia", new DateTime(2014, 2, 4, 0, 0, 0, 0))));
        microsoft.getPeople().add(new PersonAssociation(johnThompson, "Chairmain",
                new Source("Wikipedia", new DateTime(2014, 2, 4, 0, 0, 0, 0))));
        microsoft.getPeople().add(new PersonAssociation(bradSmith, "President",
                new Source("Wikipedia", new DateTime(2015, 9, 11, 0, 0, 0, 0))));

        Company amazon = new Company("Amazon");
        companies.put(amazon.getName(), amazon);
        amazon.getAliases().addAll(new ArrayList<>(Arrays.asList("Amazon.com")));
        amazon.getSymbols().add("AMZN");

        Company alphabet = new Company("Alphabet");
        companies.put(alphabet.getName(), alphabet);
        alphabet.getAliases().addAll(new ArrayList<>(Arrays.asList("Google", "Alphabet Incorporated", "Alphabet Inc", "Alphabet Inc.")));
        alphabet.getSymbols().addAll(new ArrayList<>(Arrays.asList("GOOG", "GOOGL")));

        Company berkshireHathaway = new Company("Berkshire Hathaway");
        companies.put(berkshireHathaway.getName(), berkshireHathaway);
        berkshireHathaway.getAliases().addAll(new ArrayList<>(Arrays.asList("Berkshire Hathaway Incorporated", "Berkshire Hathaway Inc", "Berkshire Hathaway Inc.")));
        berkshireHathaway.getSymbols().addAll(new ArrayList<>(Arrays.asList("BRK.B", "BRKB", "BRK-B")));


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String companiesAsStr = objectMapper.writeValueAsString(companies);
        System.out.println(companiesAsStr);
    }
}