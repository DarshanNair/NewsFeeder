package news.agoda.com.sample.core.injection.qualifiers;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Qualifier used to mark instances managed by Dagger related to a fragment.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForFragment {
}
