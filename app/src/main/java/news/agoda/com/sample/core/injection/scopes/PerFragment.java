package news.agoda.com.sample.core.injection.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Fragment scope for Dagger.
 */
@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}