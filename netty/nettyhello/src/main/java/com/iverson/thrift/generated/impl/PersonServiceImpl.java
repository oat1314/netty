package com.iverson.thrift.generated.impl;

import com.iverson.thrift.generated.DataException;
import com.iverson.thrift.generated.Person;
import com.iverson.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @program: netty-hello
 * @author: ouguoxin
 * @create: 2020-09-25 10:15
 **/

public class PersonServiceImpl implements PersonService.Iface {


    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Got Client Param:" + username);

        Person person = new Person();

        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got client Param:");

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}

