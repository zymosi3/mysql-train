package training;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import training.data.StudentDaoImpl;
import training.data.StudentDao;
import training.data.config.Config;
import training.model.Student;

import static spark.Spark.*;

public class Service {

    private static final Logger log = LoggerFactory.getLogger(Service.class);

    private static final int PORT = 8080;

    public static void main(String[] args) {

        final Config config = new Config("service.properties", 1);
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(config.url);
        dataSource.setUser(config.user);
        dataSource.setPassword(config.password);
        final StudentDao studentDao = new StudentDaoImpl(dataSource);
        port(PORT);
        get("/student", (request, response) -> {
            try {
                long id = Long.parseLong(request.queryParams("id"));
                log.info("Get student by id = {}", id);
                Student student = studentDao.get(id);
                return student != null ? studentDao.get(id).toJson() : null;
            } catch (Exception e) {
                log.error("Internal error", e);
                throw e;
            }
        });
        post("/student", (request, response) -> {
            try {
                log.info("Create update student {}", request.body());
                studentDao.write(Student.fromJson(request.body()));
                return "";
            } catch (Exception e) {
                log.error("Internal error", e);
                throw e;
            }
        });
        delete("/student", (request, response) -> {
            try {
                long id = Long.parseLong(request.queryParams("id"));
                log.info("Delete student by id = {}", id);
                studentDao.remove(id);
                return "";
            } catch (Exception e) {
                log.error("Internal error", e);
                throw e;
            }
        });
        awaitInitialization();

        log.info("Service started");
    }
}
