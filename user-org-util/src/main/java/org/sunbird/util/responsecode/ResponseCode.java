package org.sunbird.util.responsecode;

import org.apache.commons.lang3.StringUtils;

public enum ResponseCode {
  unAuthorized("UNAUTHORIZED_USER", "You are not authorized."),
  invalidUserCredentials("INVALID_USER_CREDENTIALS", "Please check your credentials"),
  operationTimeout(
      "PROCESS_EXE_TIMEOUT", "Request processing taking too long time. Please try again later."),
  invalidOperationName(
      "INVALID_OPERATION_NAME", "Operation name is invalid. Please provide a valid operation name"),
  invalidRequestData("INVALID_REQUESTED_DATA", "Requested data for this operation is not valid."),
  invalidCustomerId("CONSUMER_ID_REQUIRED_ERROR", "Consumer id is mandatory."),
  customerIdRequired("CONSUMER_ID_INVALID_ERROR", "Consumer id is invalid."),
  deviceIdRequired("DEVICE_ID_REQUIRED_ERROR", "Device id is mandatory."),
  invalidContentId("CONTENT_ID_INVALID_ERROR", "Please provide a valid content id."),
  courseIdRequired("COURSE_ID_REQUIRED_ERROR", "Please provide course id."),
  contentIdRequired("CONTENT_ID_REQUIRED_ERROR", "Please provide content id."),
  errorInvalidConfigParamValue(
      "ERROR_INVALID_CONFIG_PARAM_VALUE", "Invalid value {0} for config parameter {1}."),
  errorMaxSizeExceeded("ERROR_MAX_SIZE_EXCEEDED", "Size of {0} exceeds max limit {1}"),
  apiKeyRequired("API_KEY_REQUIRED_ERROR", "APi key is mandatory."),
  invalidApiKey("API_KEY_INVALID_ERROR", "APi key is invalid."),
  internalError("INTERNAL_ERROR", "Process failed,please try again later."),
  dbInsertionError("DB_INSERTION_FAIL", "DB insert operation failed."),
  dbUpdateError("DB_UPDATE_FAIL", "Db update operation failed."),
  courseNameRequired("COURSE_NAME_REQUIRED_ERROR", "Please provide the course name."),
  success("SUCCESS", "Success"),
  sessionIdRequiredError("SESSION_ID_REQUIRED_ERROR", "Session id is mandatory."),
  courseIdRequiredError("COURSE_ID_REQUIRED_ERROR", "Course id is mandatory."),
  contentIdRequiredError("CONTENT_ID_REQUIRED_ERROR", "Content id is mandatory."),
  versionRequiredError("VERSION_REQUIRED_ERROR", "Version is mandatory."),
  courseVersionRequiredError("COURSE_VERSION_REQUIRED_ERROR", "Course version is mandatory."),
  contentVersionRequiredError("CONTENT_VERSION_REQUIRED_ERROR", "Content version is mandatory."),
  courseDescriptionError("COURSE_DESCRIPTION_REQUIRED_ERROR", "Description is mandatory."),
  courseTocUrlError("COURSE_TOCURL_REQUIRED_ERROR", "Course tocurl is mandatory."),
  emailRequired("EMAIL_ID_REQUIRED_ERROR", "Email is mandatory."),
  emailFormatError("EMAIL_FORMAT_ERROR", "Email is invalid."),
  urlFormatError("URL_FORMAT_ERROR", "URL is invalid."),
  firstNameRequired("FIRST_NAME_REQUIRED_ERROR", "First name is mandatory."),
  languageRequired("LANGUAGE_REQUIRED_ERROR", "Language is mandatory."),
  passwordRequired("PASSWORD_REQUIRED_ERROR", "Password is mandatory."),
  passwordMinLengthError("PASSWORD_MIN_LENGHT_ERROR", "Password should have at least 8 character."),
  passwordMaxLengthError(
      "PASSWORD_MAX_LENGHT_ERROR", "Password should not be more than 12 character."),
  organisationIdRequiredError("ORGANIZATION_ID_MISSING", "Organization id is mandatory."),
  sourceAndExternalIdValidationError(
      "REQUIRED_DATA_MISSING",
      "Organization Id or Provider with External Id values are required for the operation"),
  organisationNameRequired("ORGANIZATION_NAME_MISSING", "organization name is mandatory."),
  channelUniquenessInvalid(
      "CHANNEL_SHOULD_BE_UNIQUE",
      "Channel value already used by another organization. Provide different value for channel"),
  errorDuplicateEntry("ERROR_DUPLICATE_ENTRY", "Value {0} for {1} is already in use."),
  unableToConnect("UNABLE_TO_CONNECT_TO_EKSTEP", "Unable to connect to Ekstep Server"),
  unableToConnectToES("UNABLE_TO_CONNECT_TO_ES", "Unable to connect to Elastic Search"),
  unableToParseData("UNABLE_TO_PARSE_DATA", "Unable to parse the data"),
  invalidJsonData("INVALID_JSON", "Unable to process object to JSON/ JSON to Object"),
  invalidOrgData(
      "INVALID_ORGANIZATION_DATA",
      "Given Organization Data doesn't exist in our records. Please provide a valid one"),
  invalidRootOrganisationId("INVALID ROOT ORGANIZATION", "Root organization id is invalid"),
  invalidParentId("INVALID_PARENT_ORGANIZATION_ID", "Parent organization id is invalid"),
  cyclicValidationError(
      "CYCLIC_VALIDATION_FAILURE", "The relation cannot be created as it is cyclic"),
  invalidUsrData(
      "INVALID_USER_DATA",
      "Given User Data doesn't exist in our records. Please provide a valid one"),
  usrValidationError(
      "USER_DATA_VALIDATION_ERROR", "Please provide valid userId or userName and provider"),
  errorInvalidOTP("ERROR_INVALID_OTP", "Invalid OTP."),
  enrollmentStartDateRequiredError(
      "ENROLLMENT_START_DATE_MISSING", "Enrollment start date is mandatory."),
  courseDurationRequiredError("COURSE_DURATION_MISSING", "Course duration is mandatory."),
  loginTypeRequired("LOGIN_TYPE_MISSING", "Login type is required."),
  emailAlreadyExistError("EMAIL_IN_USE", "Email already exists."),
  invalidCredentials("INVALID_CREDENTIAL", "Invalid credential."),
  userNameRequired("USERNAME_MISSING", "Username is mandatory."),
  userNameAlreadyExistError("USERNAME_IN_USE", "Username already exists."),
  userIdRequired("USERID_MISSING", "UserId is mandatory."),
  roleRequired("ROLE_MISSING", "Role of the user is required"),
  msgIdRequiredError("MESSAGE_ID_MISSING", "Message id is mandatory."),
  userNameCanntBeUpdated("USERNAME_CANNOT_BE_UPDATED", "UserName cann't be updated."),
  authTokenRequired("X_Authenticated_Userid_MISSING", "Auth token is mandatory."),
  invalidAuthToken("INVALID_AUTH_TOKEN", "Auth token is invalid.Please login again."),
  timeStampRequired("TIMESTAMP_REQUIRED", "TimeStamp is required."),
  publishedCourseCanNotBeUpdated(
      "PUBLISHED_COURSE_CAN_NOT_UPDATED", "Published course can't be updated."),
  sourceRequired("SOURCE_MISSING", "Source is required."),
  sectionNameRequired("SECTION_NAME_MISSING", "Section name is required."),
  sectionDataTypeRequired("SECTION_DATA_TYPE_MISSING", "Section data type missing."),
  sectionIdRequired("SECTION_ID_REQUIRED", "Section id is required."),
  pageNameRequired("PAGE_NAME_REQUIRED", "Page name is required."),
  pageIdRequired("PAGE_ID_REQUIRED", "Page id is required."),
  invaidConfiguration("INVALID_CONFIGURATION", "Invalid configuration data."),
  assessmentItemIdRequired("ASSESSMENT_ITEM_ID_REQUIRED", "Assessment item id is required."),
  assessmentTypeRequired("ASSESSMENT_TYPE_REQUIRED", "Assessment type is required."),
  assessmentAttemptDateRequired("ATTEMPTED_DATE_REQUIRED", "Attempted data is required."),
  assessmentAnswersRequired("ATTEMPTED_ANSWERS_REQUIRED", "Attempted answers is required."),
  assessmentmaxScoreRequired("MAX_SCORE_REQUIRED", "Max score is required."),
  statusCanntBeUpdated("STATUS_CANNOT_BE_UPDATED", "status cann't be updated."),
  attemptIdRequired("ATTEMPT_ID_REQUIRED_ERROR", "Please provide attempt id."),
  emailANDUserNameAlreadyExistError(
      "USERNAME_EMAIL_IN_USE",
      "Username or Email is already in use. Please try with a different Username or Email."),
  keyCloakDefaultError("KEY_CLOAK_DEFAULT_ERROR", "server error at sso."),
  userRegUnSuccessfull("USER_REG_UNSUCCESSFUL", "User Registration unsuccessful."),
  userUpdationUnSuccessfull("USER_UPDATE_UNSUCCESSFUL", "User update operation is unsuccessful."),
  loginTypeError("LOGIN_TYPE_ERROR", "provide login type as null."),
  invalidOrgId("INVALID_ORG_ID", "INVALID_ORG_ID"),
  invalidOrgStatus("INVALID_ORG_STATUS", "INVALID_ORG_STATUS"),
  invalidOrgStatusTransition("INVALID_ORG_STATUS_TRANSITION", "INVALID_ORG_STATUS_TRANSITION"),
  addressRequired("ADDRESS_REQUIRED_ERROR", "Please provide address."),
  educationRequired("EDUCATION_REQUIRED_ERROR", "Please provide education details."),
  phoneNoRequired("PHONE_NO_REQUIRED_ERROR", "Phone number is required."),
  jobDetailsRequired("JOBDETAILS_REQUIRED_ERROR", "Please provide job details."),
  dataAlreadyExist("DATA_ALREADY_EXIST", "data already exist."),
  invalidData("INVALID_DATA", "Incorrect data."),
  invalidCourseId(
      "INVALID_COURSE_ID", "Course doesnot exist. Please provide a valid course identifier"),
  orgIdRequired("ORG_ID_MISSING", "Organization Id required."),
  actorConnectionError("ACTOR_CONNECTION_ERROR", "Service is not able to connect with actor."),
  userAlreadyExists("USER_ALREADY_EXISTS", "User already exists for given {0}."),
  invalidUserId("INVALID_USER_ID", "User Id does not exists in our records"),
  loginIdRequired("LOGIN_ID_MISSING", "loginId is required."),
  contentStatusRequired("CONTENT_STATUS_MISSING_ERROR", "content status is required ."),
  esError("ELASTICSEARCH_ERROR", "Something went wrong when processing data for search"),
  invalidPeriod("INVALID_PERIOD", "Time Period is invalid"),
  userNotFound("USER_NOT_FOUND", "user not found."),
  idRequired("ID_REQUIRED_ERROR", "For deleting a record, Id is required."),
  dataTypeError("DATA_TYPE_ERROR", "Data type of {0} should be {1}."),
  errorAttributeConflict(
      "ERROR_ATTRIBUTE_CONFLICT", "Either pass attribute {0} or {1} but not both."),
  addressError("ADDRESS_ERROR", "In {0}, {1} is mandatory."),
  addressTypeError("ADDRESS_TYPE_ERROR", "Please provide correct address Type."),
  educationNameError("NAME_OF_INSTITUTION_ERROR", "Please provide name of Institution."),
  jobNameError("JOB_NAME_ERROR", "Job Name is required."),
  educationDegreeError("EDUCATION_DEGREE_ERROR", "Education degree is required."),
  organisationNameError("NAME_OF_ORGANIZATION_ERROR", "Organization Name is required."),
  rolesRequired("ROLES_REQUIRED_ERROR", "user role is required."),
  emptyRolesProvided("EMPTY_ROLES_PROVIDED", "Roles cannot be empty."),
  invalidDateFormat(
      "INVALID_DATE_FORMAT",
      "Invalid Date format . Date format should be : yyyy-MM-dd hh:mm:ss:SSSZ"),
  sourceAndExternalIdAlreadyExist(
      "SRC_EXTERNAL_ID_ALREADY_EXIST", "PROVIDER WITH EXTERNAL ID ALREADY EXIST ."),
  userAlreadyEnrolledCourse(
      "USER_ALREADY_ENROLLED_COURSE", "User has already Enrolled this course ."),
  userNotEnrolledCourse("USER_NOT_ENROLLED_COURSE", "User is not enrolled to given course batch."),
  courseBatchAlreadyCompleted(
      "COURSE_BATCH_ALREADY_COMPLETED", "Course batch is already completed."),
  userAlreadyCompletedCourse(
      "USER_ALREADY_COMPLETED_COURSE", "User already completed given course batch."),
  pageAlreadyExist("PAGE_ALREADY_EXIST", "page already exist with this Page Name and Org Code."),
  contentTypeRequiredError(
      "CONTENT_TYPE_ERROR", "Please add Content-Type header with value application/json"),
  invalidPropertyError("INVALID_PROPERTY_ERROR", "Invalid property {0}."),
  usernameOrUserIdError("USER_NAME_OR_ID_ERROR", "Please provide either username or userId."),
  emailVerifiedError(
      "EMAIL_VERIFY_ERROR", "Please provide a verified email in order to create user."),
  phoneVerifiedError(
      "PHONE_VERIFY_ERROR",
      "Please provide a verified phone number in order to create/update user."),
  bulkUserUploadError(
      "BULK_USER_UPLOAD_ERROR",
      "Please provide either organization Id or external Id & provider value."),
  dataSizeError("DATA_SIZE_EXCEEDED", "Maximum upload data size should be {0}"),
  InvalidColumnError("INVALID_COLUMN_NAME", "Invalid column name."),
  userAccountlocked("USER_ACCOUNT_BLOCKED", "User account has been blocked ."),
  userAlreadyActive("USER_ALREADY_ACTIVE", "User is already active."),
  userAlreadyInactive("USER_ALREADY_INACTIVE", "User is already inactive."),
  enrolmentTypeRequired("ENROLMENT_TYPE_REQUIRED", "Enrolment type is mandatory."),
  enrolmentIncorrectValue(
      "ENROLMENT_TYPE_VALUE_ERROR", "EnrolmentType value must be either open or invite-only."),
  courseBatchStartDateRequired(
      "COURSE_BATCH_START_DATE_REQUIRED", "Batch start date is mandatory."),
  courseBatchStartDateError(
      "COURSE_BATCH_START_DATE_INVALID", "Batch start date should be either today or future date."),
  dateFormatError("DATE_FORMAT_ERRROR", "Date format error."),
  endDateError("END_DATE_ERROR", "End date should be greater than start date."),
  csvError("INVALID_CSV_FILE", "Please provide valid csv file."),
  invalidCourseBatchId("INVALID_COURSE_BATCH_ID", "Invalid course batch id "),
  courseBatchIdRequired("COURSE_BATCH_ID_MISSING", "Course batch Id required"),
  enrollmentTypeValidation("ENROLLMENT_TYPE_VALIDATION", "Enrollment type should be invite-only."),
  courseCreatedForIsNull("COURSE_CREATED_FOR_NULL", "Batch does not belong to any organization ."),
  userNotAssociatedToOrg("USER_NOT_BELONGS_TO_ANY_ORG", "User does not belongs to any org ."),
  invalidObjectType("INVALID_OBJECT_TYPE", "Invalid Object Type."),
  progressStatusError(
      "INVALID_PROGRESS_STATUS",
      "Progress status value should be NOT_STARTED(0), STARTED(1), COMPLETED(2)."),
  courseBatchStartPassedDateError(
      "COURSE_BATCH_START_PASSED_DATE_INVALID", "This Batch already started."),
  csvFileEmpty("EMPTY_CSV_FILE", "CSV file is Empty."),
  invalidRootOrgData(
      "INVALID_ROOT_ORG_DATA", "Root org doesn't exist for this Organization Id and channel {0}"),
  noDataForConsumption("NO_DATA", "No sufficient data for fetching the results"),
  invalidChannel("INVALID_CHANNEL", "Channel value is invalid."),
  invalidProcessId("INVALID_PROCESS_ID", "Invalid Process Id."),
  emailSubjectError("EMAIL_SUBJECT_ERROR", "Email Subject is mandatory."),
  emailBodyError("EMAIL_BODY_ERROR", "Email Body is mandatory."),
  recipientAddressError(
      "RECIPIENT_ADDRESS_ERROR", "Please send recipientEmails or recipientUserIds."),
  storageContainerNameMandatory(
      "STORAGE_CONTAINER_NAME_MANDATORY", " Container name can not be null or empty."),
  userOrgAssociationError(
      "USER_ORG_ASSOCIATION_ERROR", "User is already associated with another organization."),
  cloudServiceError("CLOUD_SERVICE_ERROR", "Cloud storage service error."),
  badgeTypeIdMandatory("BADGE_TYPE_ID_ERROR", "Badge type id is mandatory."),
  receiverIdMandatory("RECEIVER_ID_ERROR", "Receiver id is mandatory."),
  invalidReceiverId("INVALID_RECEIVER_ID", "Receiver id is invalid."),
  invalidBadgeTypeId("INVALID_BADGE_ID", "Invalid badge type id."),
  invalidRole("INVALID_ROLE", "Invalid role value provided in request."),
  saltValue("INVALID_SALT", "Please provide salt value."),
  orgTypeMandatory("ORG_TYPE_MANDATORY", "Org Type name is mandatory."),
  orgTypeAlreadyExist(
      "ORG_TYPE_ALREADY_EXIST",
      "Org type with this name already exist.Please provide some other name."),
  orgTypeIdRequired("ORG_TYPE_ID_REQUIRED_ERROR", "Org Type Id is required."),
  titleRequired("TITLE_REQUIRED", "Title is required"),
  noteRequired("NOTE_REQUIRED", "No data to store for notes"),
  contentIdError("CONTENT_ID_OR_COURSE_ID_REQUIRED", "Please provide content id or course id"),
  invalidTags("INVALID_TAGS", "Invalid data for tags"),
  invalidNoteId("NOTE_ID_INVALID", "Invalid note id"),
  userDataEncryptionError(
      "USER_DATA_ENCRYPTION_ERROR", "Exception Occurred while encrypting user data."),
  phoneNoFormatError("INVALID_PHONE_NO_FORMAT", "Please provide a valid phone number."),
  invalidWebPageData("INVALID_WEBPAGE_DATA", "Invalid webPage data"),
  invalidMediaType("INVALID_MEDIA_TYPE", "Invalid media type for webPage"),
  invalidWebPageUrl("INVALID_WEBPAGE_URL", "Invalid URL for {0}."),
  invalidDateRange("INVALID_DATE_RANGE", "Date range should be between 3 Month."),
  invalidBatchEndDateError("INVALID_BATCH_END_DATE_ERROR", "Please provide valid End Date."),
  invalidBatchStartDateError("INVALID_BATCH_START_DATE_ERROR", "Please provide valid Start Date."),
  courseBatchEndDateError("COURSE_BATCH_END_DATE_ERROR", "Batch has been closed."),
  BatchCloseError("COURSE_BATCH_IS_CLOSED_ERROR", "Batch has been closed."),
  newPasswordRequired("CONFIIRM_PASSWORD_MISSING", "Confirm password is mandatory."),
  newPasswordEmpty("CONFIIRM_PASSWORD_EMPTY", "Confirm password can not be empty."),
  samePasswordError("SAME_PASSWORD_ERROR", "New password can't be same as old password."),
  endorsedUserIdRequired("ENDORSED_USER_ID_REQUIRED", " Endorsed user id required ."),
  canNotEndorse("CAN_NOT_ENDORSE", "Can not endorse since both belong to different orgs ."),
  invalidOrgTypeId("INVALID_ORG_TYPE_ID_ERROR", "Please provide valid orgTypeId."),
  invalidOrgType("INVALID_ORG_TYPE_ERROR", "Please provide valid orgType."),
  tableOrDocNameError("TABLE_OR_DOC_NAME_ERROR", "Please provide valid table or documentName."),
  emailorPhoneRequired("EMAIL_OR_PHONE_MISSING", "Please provide either email or phone."),
  PhoneNumberInUse(
      "PHONE_ALREADY_IN_USE", "Phone already in use. Please provide different phone number."),
  invalidClientName("INVALID_CLIENT_NAME", "Please provide unique valid client name"),
  invalidClientId("INVALID_CLIENT_ID", "Please provide valid client id"),
  userPhoneUpdateFailed("USER_PHONE_UPDATE_FAILED", "user phone update is failed."),
  esUpdateFailed("ES_UPDATE_FAILED", "Data insertion to ES failed."),
  updateFailed("UPDATE_FAILED", "Data updation failed due to invalid Request"),
  invalidTypeValue("INVALID_TYPE_VALUE", "INVALID_TYPE_VALUE"),
  invalidLocationId("INVALID_LOCATION_ID", "Please provide valid location id."),
  invalidHashTagId(
      "INVALID_HASHTAG_ID",
      "Please provide different hashTagId.This HashTagId is associated with some other organization."),
  invalidUsrOrgData(
      "INVALID_USR_ORG_DATA",
      "Given User Data doesn't belongs to this organization. Please provide a valid one."),
  visibilityInvalid("INVALID_VISIBILITY_REQUEST", "Private and Public fields cannot be same."),
  invalidTopic("INVALID_TOPIC_NAME", "Please provide a valid toipc."),
  invalidTopicData("INVALID_TOPIC_DATA", "Please provide valid notification data."),
  invalidNotificationType("INVALID_NOTIFICATION_TYPE", "Please provide a valid notification type."),
  notificationTypeSupport(
      "INVALID_NOTIFICATION_TYPE_SUPPORT", "Only notification type FCM is supported."),
  emailInUse("EMAIL_IN_USE", "Email already exists."),
  invalidPhoneNumber("INVALID_PHONE_NUMBER", "Please send Phone and country code seprately."),
  invalidCountryCode("INVALID_COUNTRY_CODE", "Please provide a valid country code."),
  locationIdRequired("LOCATION_ID_REQUIRED", "Please provide Location Id."),
  functionalityMissing("NOT_SUPPORTED", "Not Supported."),
  userNameOrUserIdRequired("USERNAME_USERID_MISSING", "Please provide either userName or userId."),
  channelRegFailed("CHANNEL_REG_FAILED", "Channel Registration failed."),
  invalidCourseCreatorId("INVALID_COURSE_CREATOR_ID", "Course creator id does not exist ."),
  userNotAssociatedToRootOrg(
      "USER_NOT_ASSOCIATED_TO_ROOT_ORG",
      "User (ID = {0}) not associated to course batch creator root org."),
  slugIsNotUnique(
      "SLUG_IS_NOT_UNIQUE",
      "Please provide different channel value. This channel value already exist."),
  invalidDataForCreateBadgeIssuer("INVALID_CREATE_BADGE_ISSUER_DATA", "{0}"),
  issuerIdRequired("ISSUER_ID_REQUIRED", "Please provide issuer ID."),
  badgeIdRequired("BADGE_ID_REQUIRED", "Please provide badge class ID."),
  badgeNameRequired("BADGE_NAME_REQUIRED", "Please provide badge class name."),
  badgeDescriptionRequired("BADGE_DESCRIPTION_REQUIRED", "Please provide badge class description."),
  badgeCriteriaRequired("BADGE_CRITERIA_REQUIRED", "Please provide badge class criteria."),
  rootOrgIdRequired("BADGE_ROOT_ORG_ID_REQUIRED", "Please provide root organisation ID."),
  badgeTypeRequired("BADGE_TYPE_REQUIRED", "Please provide badge class type."),
  invalidBadgeType("INVALID_BADGE_TYPE", "Please provide valid badge class type."),
  invalidBadgeSubtype("INVALID_BADGE_SUBTYPE", "Please provide valid badge class subtype."),
  invalidBadgeRole("INVALID_BADGE_ROLE", "Please provide valid badge class role(s)."),
  badgeRolesRequired("BADGE_ROLES_REQUIRED", "Please provide authorised roles for badge class."),
  badgeImageRequired("BADGE_IMAGE_REQUIRED", "Please provide badge class image."),
  recipientEmailRequired("RECIPIENT_EMAIL_REQUIRED", "Please provide recipient email."),
  evidenceRequired(
      "ASSERTION_EVIDENCE_REQUIRED", "Please provide valid assertion url as an evidence."),
  assertionIdRequired("ASSERTION_ID_REQUIRED", "Please provide assertion ID."),
  recipientIdRequired("RECIPIENT_ID_REQUIRED", "Please provide a recipient id."),
  recipientTypeRequired("RECIPIENT_TYPE_REQUIRED", "Please provide recipient type."),
  badgingserverError("BADGING_SERVER_ERROR", "Badging server is down or on high load"),
  resourceNotFound("RESOURCE_NOT_FOUND", "Requested resource not found"),
  sizeLimitExceed("MAX_ALLOWED_SIZE_LIMIT_EXCEED", "Max allowed size is {0}"),
  slugRequired("SLUG_REQUIRED", "Slug is required ."),
  invalidIssuerId("INVALID_ISSUER_ID", "Invalid issuer ID."),
  revocationReasonRequired("REVOCATION_REASON_REQUIRED", "Please provide revocation reason."),
  badgeAssertionAlreadyRevoked("ALREADY_REVOKED", "Assertion is already revoked."),
  invalidRecipientType("INVALID_RECIPIENT_TYPE", "Please provide a valid recipient type."),
  customClientError("CLIENT_ERROR", "Request failed. {0}"),
  customResourceNotFound("RESOURCE_NOT_FOUND", "{0}"),
  customServerError("SERVER_ERROR", "{0}"),
  inactiveUser("INACTIVE_USER", "User is Inactive. Please make it active to proceed."),
  userInactiveForThisOrg(
      "USER_INACTIVE_FOR_THIS_ORG",
      "User is Inactive for this org. Please make it active to proceed."),
  userUpdateToOrgFailed("USER_UPDATE_FAILED_FOR_THIS_ORG", "user updation failed for this org."),
  preferenceKeyMissing("USER_UPDATE_FAILED_FOR_THIS_ORG", "user updation failed for this org."),
  pageDoesNotExist("PAGE_NOT_EXIST", "Requested page does not exist."),
  orgDoesNotExist("ORG_NOT_EXIST", "Requested organisation does not exist."),
  invalidPageSource("INVALID_PAGE_SOURCE", "Invalid page source."),
  badgeSubTypeRequired("BADGE_SUBTYPE_REQUIRED", "Please provide badge class subtype."),
  locationTypeRequired("LOCATION_TYPE_REQUIRED", "Location type required."),
  invalidRequestDataForLocation("INVALID_REQUEST_DATA_CREATE_LOCATION", "{0} field required."),
  alreadyExists(
      "ALREADY_EXISTS", "A {0} with {1} already exists. Please retry with a unique value."),
  invalidValue("INVALID_VALUE", "Invalid {0}: {1}. Valid values are: {2}."),
  parentCodeAndIdValidationError(
      "PARENT_CODE_AND_PARENT_ID_MISSING", "Please provide either parentCode or parentId."),
  invalidParameter("INVALID_PARAMETER", "Please provide valid {0}."),
  invalidLocationDeleteRequest(
      "INVALID_LOCATION_DELETE_REQUEST",
      "One or more locations have a parent reference to given location and hence cannot be deleted."),
  locationTypeConflicts(
      "LOCATION_TYPE_CONFLICTS", "Location type conflicts with its parent location type."),
  mandatoryParamsMissing("MANDATORY_PARAMETER_MISSING", "Mandatory parameter {0} is missing."),
  errorMandatoryParamsEmpty("ERROR_MANDATORY_PARAMETER_EMPTY", "Mandatory parameter {0} is empty."),
  errorNoFrameworkFound("ERROR_NO_FRAMEWORK_FOUND", "No framework found."),
  unupdatableField("UPDATE_NOT_ALLOWED", "Update of {0} is not allowed."),
  mandatoryHeadersMissing("MANDATORY_HEADER_MISSING", "Mandatory header {0} is missing."),
  invalidParameterValue(
      "INVALID_PARAMETER_VALUE",
      "Invalid value {0} for parameter {1}. Please provide a valid value."),
  parentNotAllowed("PARENT_NOT_ALLOWED", "For top level location, {0} is not allowed."),
  missingFileAttachment("MISSING_FILE_ATTACHMENT", "Missing file attachment."),
  fileAttachmentSizeNotConfigured(
      "ATTACHMENT_SIZE_NOT_CONFIGURED", "File attachment max size is not configured."),
  emptyFile("EMPTY_FILE", "Attached file is empty."),
  invalidColumns("INVALID_COLUMNS", "Invalid column: {0}. Valid columns are: {1}."),
  conflictingOrgLocations(
      "CONFLICTING_ORG_LOCATIONS",
      "An organisation cannot be associated to two conflicting locations ({0}, {1}) at {2} level. "),
  unableToCommunicateWithActor(
      "UNABLE_TO_COMMUNICATE_WITH_ACTOR", "Unable to communicate with actor."),
  emptyHeaderLine("EMPTY_HEADER_LINE", "Missing header line in CSV file."),
  invalidRequestParameter("INVALID_REQUEST_PARAMETER", "Invalid parameter {0} in request."),
  rootOrgAssociationError(
      "ROOT_ORG_ASSOCIATION_ERROR",
      "No root organisation found which is associated with given {0}."),
  dependentParameterMissing(
      "DEPENDENT_PARAMETER_MISSING", "Missing parameter {0} which is dependent on {1}."),
  externalIdNotFound(
      "EXTERNALID_NOT_FOUND",
      "External ID (id: {0}, idType: {1}, provider: {2}) not found for given user."),
  externalIdAssignedToOtherUser(
      "EXTERNALID_ASSIGNED_TO_OTHER_USER",
      "External ID (id: {0}, idType: {1}, provider: {2}) already assigned to another user."),
  dependentParamsMissing("DEPENDENT_PARAMETER_MISSING", "Missing parameter value in {0}."),
  mandatoryConfigParamMissing(
      "MANDATORY_CONFIG_PARAMETER_MISSING",
      "Mandatory configuration parameter {0} missing which is required for service startup."),
  cassandraConnectionEstablishmentFailed(
      "CASSANDRA_CONNECTION_ESTABLISHMENT_FAILED",
      "Cassandra connection establishment failed in {0} mode."),
  commonAttributeMismatch("COMMON_ATTRIBUTE_MISMATCH", "{0} mismatch of {1} and {2}"),
  multipleCoursesNotAllowedForBatch(
      "MULTIPLE_COURSES_FOR_BATCH", "A batch cannot belong to multiple courses."),
  errorJsonTransformInvalidTypeConfig(
      "ERROR_JSON_TRANSFORM_INVALID_TYPE_CONFIG",
      "JSON transformation failed as invalid type configuration found for field {0}."),
  errorJsonTransformInvalidDateFormat(
      "ERROR_JSON_TRANSFORM_INVALID_DATE_FORMAT",
      "JSON transformation failed as invalid date format configuration found for field {0}."),
  errorJsonTransformInvalidInput(
      "ERROR_JSON_TRANSFORM_INVALID_INPUT",
      "JSON transformation failed as invalid input provided for field {0}."),
  errorJsonTransformInvalidEnumInput(
      "ERROR_JSON_TRANSFORM_INVALID_ENUM_INPUT",
      "JSON transformation failed as invalid enum input provided for field {0}."),
  errorJsonTransformEnumValuesEmpty(
      "ERROR_JSON_TRANSFORM_ENUM_VALUES_EMPTY",
      "JSON transformation failed as enum values is empty in configuration for field {0}."),
  errorJsonTransformBasicConfigMissing(
      "ERROR_JSON_TRANSFORM_BASIC_CONFIG_MISSING",
      "JSON transformation failed as mandatory configuration (toFieldName, fromType or toType) is missing for field {0}."),
  errorJsonTransformInvalidFilterConfig(
      "ERROR_JSON_TRANSFORM_INVALID_FILTER_CONFIG",
      "JSON transformation failed as invalid filter configuration found for field {0}."),
  errorLoadConfig("ERROR_LOAD_CONFIG", "Loading failed for configuration file {0}."),
  errorRegistryClientCreation("ERROR_REGISTRY_CLIENT_CREATION", "Registry client creation failed."),
  errorRegistryAddEntity("ERROR_REGISTRY_ADD_ENTITY", "Registry add entity API failed."),
  errorRegistryReadEntity("ERROR_REGISTRY_READ_ENTITY", "Registry read entity API failed."),
  errorRegistryUpdateEntity("ERROR_REGISTRY_UPDATE_ENTITY", "Registry update entity API failed."),
  errorRegistryDeleteEntity("ERROR_REGISTRY_DELETE_ENTITY", "Registry delete entity API failed."),
  errorRegistryParseResponse(
      "ERROR_REGISTRY_PARSE_RESPONSE", "Error while parsing response from registry."),
  errorRegistryEntityTypeBlank(
      "ERROR_REGISTRY_ENTITY_TYPE_BLANK", "Request failed as entity type is blank."),
  errorRegistryEntityIdBlank(
      "ERROR_REGISTRY_ENTITY_ID_BLANK", "Request failed as entity id is not provided."),
  errorRegistryAccessTokenBlank(
      "ERROR_REGISTRY_ACCESS_TOKEN_BLANK", "Request failed as user access token is not provided."),
  duplicateExternalIds(
      "DUPLICATE_EXTERNAL_IDS",
      "Duplicate external IDs for given idType ({0}) and provider ({1})."),
  invalidDuplicateValue("INVALID_DUPLICATE_VALUE", "Values for {0} and {1} cannot be same."),
  emailNotSentRecipientsExceededMaxLimit(
      "EMAIL_RECIPIENTS_EXCEEDS_MAX_LIMIT",
      "Email notification is not sent as the number of recipients exceeded configured limit ({0})."),
  emailNotSentRecipientsZero(
      "NO_EMAIL_RECIPIENTS", "Email notification is not sent as the number of recipients is zero."),
  parameterMismatch("PARAMETER_MISMATCH", "Mismatch of given parameters: {0}."),
  errorForbidden("FORBIDDEN", "You are forbidden from accessing specified resource."),
  errorConfigLoadEmptyString(
      "ERROR_CONFIG_LOAD_EMPTY_STRING",
      "Loading {0} configuration failed as empty string is passed as parameter."),
  errorConfigLoadParseString(
      "ERROR_CONFIG_LOAD_PARSE_STRING", "Loading {0} configuration failed due to parsing error."),
  errorConfigLoadEmptyConfig("ERROR_CONFIG_LOAD_EMPTY_CONFIG", "Loading {0} configuration failed."),
  errorConflictingFieldConfiguration(
      "ERROR_CONFLICTING_FIELD_CONFIGURATION",
      "Field {0} in {1} configuration is conflicting in {2} and {3}."),
  errorSystemSettingNotFound(
      "ERROR_SYSTEM_SETTING_NOT_FOUND", "System Setting not found for id: {0}"),
  errorNoRootOrgAssociated("ERROR_NO_ROOT_ORG_ASSOCIATED", "Not able to associate with root org"),
  errorInactiveCustodianOrg("ERROR_INACTIVE_CUSTODIAN_ORG", "Custodian organisation is inactive."),
  errorUnsupportedCloudStorage(
      "ERROR_ UNSUPPORTED_CLOUD_STORAGE", "Unsupported cloud storage type {0}."),
  errorUnsupportedField("ERROR_UNSUPPORTED_FIELD", "Unsupported field {0}."),
  errorGenerateDownloadLink("ERROR_GENERATING_DOWNLOAD_LINK", "Error in generating download link."),
  errorUnavailableDownloadLink("ERROR_DOWNLOAD_LINK_UNAVAILABLE", "Download link is unavailable."),
  errorSavingStorageDetails(
      "ERROR_SAVING_STORAGE_DETAILS", "Error saving storage details for download link."),
  errorCsvNoDataRows("ERROR_CSV_NO_DATA_ROWS", "No data rows in CSV."),
  errorInactiveOrg(
      "ERROR_INACTIVE_ORG", "Organisation corresponding to given {0} ({1}) is inactive."),
  errorDuplicateEntries("ERROR_DUPLICATE_ENTRIES", "System contains duplicate entry for {0}."),
  errorConflictingValues(
      "ERROR_CONFLICTING_VALUES", "Conflicting values for {0} ({1}) and {2} ({3})."),
  errorConflictingRootOrgId(
      "ERROR_CONFLICTING_ROOT_ORG_ID",
      "Root organisation ID of API user is conflicting with that of specified organisation ID."),
  errorUpdateSettingNotAllowed(
      "ERROR_UPDATE_SETTING_NOT_ALLOWED", "Update of system setting {0} is not allowed."),
  errorCreatingFile("ERROR_CREATING_FILE", "Eroor Reading File"),
  errorProcessingRequest(
      "ERROR_PROCESSING_REQUEST", "Something went wrong while Processing Request"),
  invalidTextbook(
      "INVALID_TEXTBOOK", "Invalid Textbook. Please Provide Valid Textbook Identifier."),
  csvRowsExceeds("CSV_ROWS_EXCEEDS", "Number of rows in csv file is more than "),
  invalidTextbookName(
      "INVALID_TEXTBOOK_NAME",
      "Textbook Name given in the file doesn’t match current Textbook name. Please check and upload again."),
  duplicateRows(
      "DUPLICATE_ROWS",
      "Duplicate Textbook node found. Please check and upload again. Row number "),
  requiredHeaderMissing("REQUIRED_HEADER_MISSING", "Required set of header missing: "),
  requiredFieldMissing(
      "REQUIRED_FIELD_MISSING",
      "Required columns missing. Please check and upload again. Mandatory fields are: "),
  blankCsvData(
      "BLANK_CSV_DATA", "Did not find any Table of Contents data. Please check and upload again."),
  exceedMaxChildren("EXCEEDS_MAX_CHILDREN", "Number of first level units is more than allowed."),
  textbookChildrenExist("TEXTBOOK_CHILDREN_EXISTS", "Textbook is already having children."),
  textbookUpdateFailure("TEXTBOOK_UPDATE_FAILURE", "Textbook could not be updated."),
  noChildrenExists("TEXTBOOK_CHILDREN_NOT_EXISTS", "No Children Exists for given TextBook."),
  textBookNotFound("TEXTBOOK_NOT_FOUND", "Textbook not found."),
  errorProcessingFile(
      "ERROR_PROCESSING_FILE", "Something Went Wrong While Reading File. Please Check The File."),
  fileNotFound("ERR_FILE_NOT_FOUND", "File not found. Please select valid file and upload."),
  errorTbUpdate("ERROR_TB_UPDATE", "Error while updating the textbook"),
  errorInvalidParameterSize(
      "ERROR_INVALID_PARAMETER_SIZE",
      "Parameter {0} is of invalid size (expected: {1}, actual: {2})."),
  errorInvalidPageSection(
      "INVALID_PAGE_SECTION", "Page section associated with the page is invalid."),
  errorRateLimitExceeded(
      "ERROR_RATE_LIMIT_EXCEEDED",
      "Your per {0} rate limit has exceeded. You can retry after some time."),
  errorInvalidDialCode("ERROR_INVALID_DIAL_CODE", "The given QR code {0} is not valid."),
  errorInvalidTopic(
      "ERROR_INVALID_TOPIC", "Topic {0} not found in the framework. Please check and correct."),
  errorDialCodeDuplicateEntry(
      "ERROR_DIAL_CODE_DUPLICATE_ENTRY",
      "QR code {0} is associated with more than one section {1}."),
  errorDialCodeAlreadyAssociated(
      "ERROR_DIAL_CODE_ALREADY_ASSOCIATED",
      "QR code {0} is already associated with a section {1} in the textbook"),
  errorDialCodeLinkingFail("DIAL_CODE_LINKING_FAILED", "QR code linking failed."),
  errorDialCodeLinkingClientError("ERROR_TEXTBOOK_UPDATE", "{0}"),
  errorInvalidLinkedContentId(
      "ERROR_INVALID_LINKED_CONTENT_ID", "Linked Content {0} is not valid at row {1}."),
  errorDuplicateLinkedContentId("DUPLICATE_LINKED_CONTENT", "Duplicate content {0} at row {1}."),
  errorTeacherCannotBelongToCustodianOrg(
      "TEACHER_CANNOT_BELONG_TO_CUSTODIAN_ORG",
      "User type teacher is not supported for custodian organisation users"),
  errorDduplicateDialCodeEntry(
      "ERROR_DUPLICATE_QR_CODE_ENTRY",
      "CSV file contains more than one entry for {0}. Correct the duplicate entry and try again."),
  errorInvalidTextbookUnitId(
      "ERROR_INVALID_TEXTBOOK_UNIT_ID", "Invalid textbook unit id {0} for texbook."),
  invalidRequestTimeout("INVALID_REQUEST_TIMEOUT", "Invalid request timeout value {0}."),
  errorBGMSMismatch("ERROR_BGMS_MISMATCH", "Mismatch in {0} at row - {1}"),
  errorUserMigrationFailed("ERROR_USER_MIGRATION_FAILED", "User migration failed."),
  OK(200),
  CLIENT_ERROR(400),
  SERVER_ERROR(500),
  RESOURCE_NOT_FOUND(404),
  UNAUTHORIZED(401),
  FORBIDDEN(403),
  REDIRECTION_REQUIRED(302),
  TOO_MANY_REQUESTS(429),
  SERVICE_UNAVAILABLE(503);

  private int responseCode;
  private String errorCode;
  private String errorMessage;

  private ResponseCode(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  private ResponseCode(String errorCode, String errorMessage, int responseCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.responseCode = responseCode;
  }

  public String getMessage(int errorCode) {
    return "";
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public static String getResponseMessage(String code) {
    if (StringUtils.isBlank(code)) {
      return "";
    } else {
      ResponseCode[] responseCodes = values();
      ResponseCode[] var2 = responseCodes;
      int var3 = responseCodes.length;

      for (int var4 = 0; var4 < var3; ++var4) {
        ResponseCode actionState = var2[var4];
        if (actionState.getErrorCode().equals(code)) {
          return actionState.getErrorMessage();
        }
      }

      return "";
    }
  }

  private ResponseCode(int responseCode) {
    this.responseCode = responseCode;
  }

  public int getResponseCode() {
    return this.responseCode;
  }

  public void setResponseCode(int responseCode) {
    this.responseCode = responseCode;
  }

  public static ResponseCode getHeaderResponseCode(int code) {
    if (code > 0) {
      try {
        ResponseCode[] arr = values();
        if (null != arr) {
          ResponseCode[] var2 = arr;
          int var3 = arr.length;

          for (int var4 = 0; var4 < var3; ++var4) {
            ResponseCode rc = var2[var4];
            if (rc.getResponseCode() == code) {
              return rc;
            }
          }
        }
      } catch (Exception var6) {
        return SERVER_ERROR;
      }
    }

    return SERVER_ERROR;
  }

  public static ResponseCode getResponse(String errorCode) {
    if (StringUtils.isBlank(errorCode)) {
      return null;
    } else if ("Unauthorized".equals(errorCode)) {
      return unAuthorized;
    } else {
      ResponseCode value = null;
      ResponseCode[] responseCodes = values();
      ResponseCode[] var3 = responseCodes;
      int var4 = responseCodes.length;

      for (int var5 = 0; var5 < var4; ++var5) {
        ResponseCode response = var3[var5];
        if (response.getErrorCode().equals(errorCode)) {
          return response;
        }
      }

      return (ResponseCode) value;
    }
  }
}
