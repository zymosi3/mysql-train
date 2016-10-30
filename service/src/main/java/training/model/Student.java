package training.model;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Student {

    public final long id;
    public final String name;
    public final String email;
    public final int age;
    public final String gender;
    public final String group;
    public final int year;

    public Student(long id, String name, String email, int age, String gender, String group, int year) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.group = group;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        if (age != student.age) return false;
        if (year != student.year) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (email != null ? !email.equals(student.email) : student.email != null) return false;
        if (gender != null ? !gender.equals(student.gender) : student.gender != null) return false;
        return group != null ? group.equals(student.group) : student.group == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", group='" + group + '\'' +
                ", year=" + year +
                '}';
    }

    public String toJson() {
        ObjectMapper mapper = JsonFactory.create();
        return mapper.toJson(toMap());
    }

    public Map<String, Object> toMap() {
        Map<String, Object> entity = new HashMap<>();
        entity.put("id", id);
        entity.put("name", name);
        entity.put("email", email);
        entity.put("age", age);
        entity.put("gender", gender);
        entity.put("group", group);
        entity.put("year", year);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public static Student fromJson(String json) {
        ObjectMapper mapper = JsonFactory.create();
        Map<String, Object> objectMap = (Map<String, Object>) mapper.fromJson(json, Map.class);
        return new Student(
                (int) objectMap.get("id"),
                (String) objectMap.get("name"),
                (String) objectMap.get("email"),
                (int) objectMap.get("age"),
                (String) objectMap.get("gender"),
                (String) objectMap.get("group"),
                (int) objectMap.get("year")
        );
    }
}
