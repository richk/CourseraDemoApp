package demo.catalog.coursera.org.courserademoapp.domain;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkServiceImpl;
import demo.catalog.coursera.org.courserademoapp.network.JSCourse;
import demo.catalog.coursera.org.courserademoapp.network.JSCourseResponse;
import rx.Observable;
import rx.functions.Action1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatalogInteractorTest extends TestCase {

    @SmallTest
    public void testGetCourses() {
        JSCourseResponse mockJSResponse = new JSCourseResponse();
        JSCourse[] courses = new JSCourse[1];
        JSCourse course = new JSCourse();
        course.id = "1";
        course.name = "Test Course";
        course.shortName = "test";
        courses[0] = course;
        mockJSResponse.elements = courses;
        CourseraNetworkServiceImpl mockNetworkService = mock(CourseraNetworkServiceImpl.class);
        when(mockNetworkService.getCourses()).thenReturn(Observable.just(mockJSResponse));

        mockNetworkService.getCourses().subscribe(new Action1<JSCourseResponse>() {
            @Override
            public void call(JSCourseResponse jsCourseResponse) {
                assertNotNull(jsCourseResponse);
                assertEquals(1, jsCourseResponse.elements.length);
            }
        });
    }

}
