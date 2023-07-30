package com.school.exception;

import com.school.model.Error;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {SchoolException.class, ExceptionHandler.class})
public class SchoolExceptionHandler implements ExceptionHandler<SchoolException, HttpResponse<Error>> {

    @Override
    public HttpResponse<Error> handle(HttpRequest request, SchoolException exception) {

        var error = new Error(exception.getMessage());

        return switch (exception.getClass().getSimpleName()) {
            case "SchoolException", "StudentNotFoundException", "CourseNotFoundException", "UsernameAlreadyExistsException" ->
                    HttpResponse.badRequest(error);
            case "StudentLimitExceededException", "CourseLimitExceededException" ->
                    HttpResponse.status(HttpStatus.PRECONDITION_FAILED).body(error);
            default -> HttpResponse.serverError(error);
        };

    }

}
