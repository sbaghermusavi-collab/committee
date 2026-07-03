package com.nicico.copper.common.dto.search;

public enum EOperator {
	and,// all subcriteria (criterion.criteria) are true
	or,// at least one subcriteria (criterion.criteria) is true
	not,// all subcriteria (criterion.criteria) are false

	iEquals,// exactly equal to, if case is disregarded
	equals,// exactly equal to

	iNotEqual,// not equal to, if case is disregarded
	notEqual,// not equal to

	greaterThan,// Greater than
	greaterOrEqual,// Greater than or equal to

	lessThan,// Less than
	lessOrEqual,// Less than or equal to

	iContains,// Contains as sub-string (case insensitive)
	contains,// Contains as sub-string (match case)

	iStartsWith,// Starts with (case insensitive)
	startsWith,// Starts with (match case)

	iEndsWith,// Ends with (case insensitive)
	endsWith,// Ends with (match case)

	iNotContains,// Does not contain as sub-string (case insensitive)
	notContains,// Does not contain as sub-string (match case)

	iNotStartsWith,// Does not start with (case insensitive)
	notStartsWith,// Does not start with (match case)

	iNotEndsWith,// Does not end with (case insensitive)
	notEndsWith,// Does not end with (match case)

	iBetween,// shortcut for "greaterThan" + "and" + "lessThan" (case insensitive)
	between,// shortcut for "greaterThan" + "lessThan" + "and". Specify criterion.start and criterion.end

	iBetweenInclusive,// shortcut for "greaterOrEqual" + "and" + "lessOrEqual" (case insensitive)
	betweenInclusive,// shortcut for "greaterOrEqual" + "lessOrEqual" + "and". Specify criterion.start and criterion.end

	isBlank,// value is either null or the empty string. For numeric fields it behaves as isNull

	notBlank,// value is neither null nor the empty string ("")

	inSet,// value is in a set of values. Specify criterion.value as an Array

	iMatchesPattern,// Basic GLOB matching using wildcards (case insensitive) (see DataSource.translatePatternOperators for more information on available patterns)
	matchesPattern,// Basic GLOB matching using wildcards (see DataSource.translatePatternOperators for more information on available patterns)

	iContainsPattern,// GLOB matching using wildcards. Value is considered to meet the criterion if it contains the pattern. Matching is case insensitive. See DataSource.translatePatternOperators for more information on available patterns)
	containsPattern,// GLOB matching using wildcards. Value is considered to meet the criterion if it contains the pattern. See DataSource.translatePatternOperators for more information on available patterns)

	iStartsWithPattern,// GLOB matching using wildcards. Value is considered to meet the criterion if it starts with the pattern. Matching is case insensitive.See DataSource.translatePatternOperators for more information on available patterns)
	startsWithPattern,// GLOB mathcing using wildcards. Value is considered to meet the criterion if it starts with the pattern.See DataSource.translatePatternOperators for more information on available patterns)

	iEndsWithPattern,// GLOB matching using wildcards.Value is considered to meet the criterion if it ends with the pattern. Matching is case insensitive. See DataSource.translatePatternOperators for more information on available patterns)
	endsWithPattern,// GLOB mathcing using wildcards. Value is considered to meet the criterion if it starts with the pattern.See DataSource.translatePatternOperators for more information on available patterns)

	iregexp,// Regular expression match (case insensitive)
	regexp,// Regular expression match

	isNull,// value is null
	notNull,// value is non-null. Note empty string ("") is non-null

	notInSet,// value is not in a set of values. Specify criterion.value as an Array

	iEqualsField,// matches another field (case insensitive, specify fieldName as criterion.value)
	equalsField,// matches another field (match case, specify fieldName as criterion.value)

	iNotEqualField,// does not match another field (case insensitive, specify fieldName as criterion.value)
	notEqualField,// does not match another field (match case, specify fieldName as criterion.value)

	greaterThanField,// Greater than another field (specify fieldName as criterion.value)
	greaterOrEqualField,// Greater than or equal to another field (specify fieldName as criterion.value)

	lessThanField,// Less than another field (specify fieldName as criterion.value)
	lessOrEqualField,// Less than or equal to another field (specify fieldName as criterion.value)

	iContainsField,// Contains as sub-string (case insensitive) another field value (specify fieldName as criterion.value)
	containsField,// Contains as sub-string (match case) another field value (specify fieldName as criterion.value)

	iStartsWithField,// Starts with (case insensitive) another field value (specify fieldName as criterion.value)
	startsWithField,// Starts with (match case) another field value (specify fieldName as criterion.value)

	endsWithField,// Ends with (match case) another field value (specify fieldName as criterion.value)
	iEndsWithField,// Ends with (case insensitive) another field value (specify fieldName as criterion.value)

	iNotContainsField,// Does not contain as sub-string (case insensitive) another field value (specify fieldName as criterion.value)
	notContainsField,// Does not contain as sub-string (match case) another field value (specify fieldName as criterion.value)

	iNotStartsWithField,// Does not start with (case insensitive) another field value (specify fieldName as criterion.value)
	notStartsWithField,// Does not start with (match case) another field value (specify fieldName as criterion.value)

	iNotEndsWithField,// Does not end with (case insensitive) another field value (specify fieldName as criterion.value)
	notEndsWithField,// Does not end with (match case) another field value (specify fieldName as criterion.value)
	replace
}
