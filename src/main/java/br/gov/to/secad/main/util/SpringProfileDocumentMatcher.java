package br.gov.to.secad.main.util;

import org.springframework.beans.factory.config.YamlProcessor.DocumentMatcher;
import org.springframework.beans.factory.config.YamlProcessor.MatchStatus;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Allows to use Spring profiles without Spring Boot for legacy Spring based apps
 *
 * @author Alexandre de Pellegrin
 */
public class SpringProfileDocumentMatcher implements DocumentMatcher, EnvironmentAware {

    private static final String[] DEFAULT_PROFILES = new String[]{"default"};

    private String[] activeProfiles = new String[0];

    public SpringProfileDocumentMatcher() {
    }

    public SpringProfileDocumentMatcher(String... profiles) {
        addActiveProfiles(profiles);
    }

    private void addActiveProfiles(String... profiles) {
        LinkedHashSet<String> set = new LinkedHashSet<>(Arrays.asList(this.activeProfiles));
        Collections.addAll(set, profiles);
        this.activeProfiles = set.toArray(new String[set.size()]);
    }

    @Override
    public MatchStatus matches(Properties properties) {
        String[] profiles = this.activeProfiles;
        if (profiles.length == 0) {
            profiles = DEFAULT_PROFILES;
        }
        return new ArrayDocumentMatcher("spring.profiles", profiles).matches(properties);
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (environment != null) {
            addActiveProfiles(environment.getActiveProfiles());
        }
    }


    private class ArrayDocumentMatcher implements DocumentMatcher {
        private final String key;
        private final String[] patterns;

        private ArrayDocumentMatcher(final String key, final String... patterns) {
            this.key = key;
            this.patterns = patterns;
        }

        @Override
        public MatchStatus matches(Properties properties) {
            if (!properties.containsKey(this.key)) {
                return MatchStatus.ABSTAIN;
            }
            Set values = StringUtils.commaDelimitedListToSet(properties
                    .getProperty(this.key));
            for (String pattern : this.patterns) {
                for (Object value : values) {
                    if (value.toString().matches(pattern)) {
                        return MatchStatus.FOUND;
                    }
                }
            }
            return MatchStatus.NOT_FOUND;
        }
    }
}