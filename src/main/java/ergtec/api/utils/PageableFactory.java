package ergtec.api.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableFactory {

    private PageableFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable getPageable(int pageNo, int pageSize, String sortBy) {
        if (sortBy.contains(",")) {
            String [] sortArray = sortBy.split(",");
            String sortParameter = "album";
            if (sortArray[0].equals("image"))
                sortParameter = "title";

            if (sortArray[1].equals("desc"))
                return PageRequest.of(pageNo, pageSize, Sort.by(sortParameter).descending());

            return PageRequest.of(pageNo, pageSize, Sort.by(sortParameter).ascending());
        }

        return PageRequest.of(pageNo, pageSize);
    }
}
