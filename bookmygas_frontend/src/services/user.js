import api from './config';

export async function register(name, email, password, userType, phoneNumber, address) {
  try {
    // post body
    const body = {
      name, email, password, userType, phoneNumber, address
    }

    // send the post request
    const response = await api.post(`/users/signup`, body)

    // return the json body from response object
    return response
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  return null
}

export async function login(email, password) {
  const body = { email, password }
  try {
    const response = await api.post(`/users/signin`, body)
    return response
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  return null
}