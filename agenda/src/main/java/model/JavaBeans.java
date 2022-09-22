package model;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {

/** The id. */
private String id;

/** The nome. */
private String nome;

/** The num. */
private String num;

/** The email. */
private String email;



/**
 * Instantiates a new java beans.
 */
public JavaBeans() {
	super();
	
}


/**
 * Instantiates a new java beans.
 *
 * @param id the id
 * @param nome the nome
 * @param num the num
 * @param email the email
 */
public JavaBeans(String id, String nome, String num, String email) {
	super();
	this.id = id;
	this.nome = nome;
	this.num = num;
	this.email = email;
}




/**
 * Gets the id.
 *
 * @return the id
 */
public String getId() {
	return id;
}

/**
 * Sets the id.
 *
 * @param id the new id
 */
public void setId(String id) {
	this.id = id;
}

/**
 * Gets the nome.
 *
 * @return the nome
 */
public String getNome() {
	return nome;
}

/**
 * Sets the nome.
 *
 * @param nome the new nome
 */
public void setNome(String nome) {
	this.nome = nome;
}

/**
 * Gets the num.
 *
 * @return the num
 */
public String getNum() {
	return num;
}

/**
 * Sets the num.
 *
 * @param num the new num
 */
public void setNum(String num) {
	this.num = num;
}

/**
 * Gets the email.
 *
 * @return the email
 */
public String getEmail() {
	return email;
}

/**
 * Sets the email.
 *
 * @param email the new email
 */
public void setEmail(String email) {
	this.email = email;
}


}
