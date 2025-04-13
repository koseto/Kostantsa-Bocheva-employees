import service.EmployeeOverlapService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            return;
        }

        EmployeeOverlapService.findPairsThatWorkedTogether(args[0]);
    }
}
