package mocha.yusuf.jetpack3.Data.Source.Remote;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static mocha.yusuf.jetpack3.Data.Source.Remote.Status.EMPTY;
import static mocha.yusuf.jetpack3.Data.Source.Remote.Status.ERROR;
import static mocha.yusuf.jetpack3.Data.Source.Remote.Status.SUCCESS;

public class ApiResponse <T> {
    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    @Nullable
    public final T body;

    private ApiResponse(@NonNull Status statusResponse, @Nullable String message, @Nullable T body) {
        this.status = statusResponse;
        this.message = message;
        this.body = body;
    }

    public static <T> ApiResponse success(@Nullable T body) {
        return new ApiResponse<>(SUCCESS, null, body);
    }

    public static <T> ApiResponse empty(String message, @Nullable T body) {
        return new ApiResponse<>(EMPTY, message, body);
    }

    public static <T> ApiResponse error(String message, @Nullable T body) {
        return new ApiResponse<>(ERROR, message, body);
    }
}
