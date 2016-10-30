package training.data;

import training.model.Student;

public interface StudentDao {

    /**
     * Get student by id
     */
    Student get(long id);

    /**
     * Create new student or update if exists
     */
    void write(Student student);

    /**
     * Delete student by id
     */
    void remove(long id);
}
