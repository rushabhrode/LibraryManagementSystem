import React from 'react'
import { Link } from 'react-router-dom'
import './navbar.css'
import navbarData from './navbardata'
import UserLogin from './UserIsLoggedIn'
import UserSignin from './UserIsNotLoggedIn'
import { useLoginState } from '../../LoginState'
import { useTranslation } from 'react-i18next'

const Navbar = () => {
  const userLoginState = useLoginState()
  const { t, i18n } = useTranslation()
  const { navbarLinks, navbarImage, navbarLinksNotAuthenticated, navbarLinksIsAuthenticated } = navbarData

  const changeLanguage = (e) => {
    i18n.changeLanguage(e.target.value)
  }

  return (
    <nav className='navbar navbar-expand-xl'>
      <div className='container-fluid'>
        <Link to='/' className='a'>
          <img src={navbarImage} alt='Logo' width='50' className='d-sm-inline-block d-none' />
          <h4 className='h4 d-inline' id='navbar-title-text'>
            {t('navbar.title')}
          </h4>
        </Link>

        <button
          id='navbar-mobileview-btn'
          className='navbar-toggler'
          type='button'
          data-bs-toggle='collapse'
          data-bs-target='#navbarSupportedContent'
          aria-controls='navbarSupportedContent'
          aria-expanded='false'
          aria-label='Toggle navigation'
        >
          <span className='navbar-toggler-icon' />
        </button>

        <div className='collapse navbar-collapse' id='navbarSupportedContent'>
          <ul className='navbar-nav mb-2 mb-lg-0 justify-content-end fs-5'>
            {(userLoginState.userLogState ? navbarLinksIsAuthenticated : navbarLinksNotAuthenticated)
              .concat(navbarLinks)
              .map((link, index) => (
                <li className='nav-item' key={index}>
                  <Link to={link.url} className='nav-link'>
                    {t(`navbar.links.${link.key}`)}
                  </Link>
                </li>
              ))}
          </ul>

          {/* Language switcher */}
          <div className='ms-auto'>
            <select onChange={changeLanguage} className='form-select' style={{ width: '120px' }}>
              <option value='en'>English</option>
              <option value='hi'>हिन्दी</option>
              <option value='mr'>मराठी</option>
            </select>
          </div>
        </div>
      </div>
    </nav>
  )
}

export default Navbar


// import React, { useContext, useEffect, useState } from 'react'
// import { Link } from 'react-router-dom'
// import './navbar.css'
// import { useTranslation } from 'react-i18next'


// // Importing all Navbar data's i.e. title,logo,links,etc
// import navbarData from './navbardata'

// // Conditionally rendering links based on user's login info
// import UserLogin from './UserIsLoggedIn'
// import UserSignin from './UserIsNotLoggedIn'

// // navbar search option
// import NavbarSearch from './NavbarSearch'

// import { useLoginState } from '../../LoginState'



// const Navbar = () => {
//   const [isLoggedin, setIsLoggedin] = useState(false)

//   const userLoginState = useLoginState()

//   const { navbarLinks, navbarTitle, navbarImage } = navbarData

//   const { t, i18n } = useTranslation()

//   const changeLanguage = (e) => {
//     i18n.changeLanguage(e.target.value)
//   }

//   return (
//     <nav className='navbar navbar-expand-xl '>
//       <div className='container-fluid'>
//         <Link to='/' className='a'>
//           <img
//             src={navbarImage}
//             alt='Logo'
//             width={'50'}
//             className='d-sm-inline-block d-none'
//           />
//           <h4 className='h4 d-inline ' id='navbar-title-text'>
//             {navbarTitle}
//           </h4>
//         </Link>

//         <button
//           id='navbar-mobileview-btn'
//           className='navbar-toggler'
//           type='button'
//           data-bs-toggle='collapse'
//           data-bs-target='#navbarSupportedContent'
//           aria-controls='navbarSupportedContent'
//           aria-expanded='false'
//           aria-label='Toggle navigation'
//         >
//           <span className='navbar-toggler-icon' />
//         </button>

//         <div className='collapse navbar-collapse' id='navbarSupportedContent'>
//           <ul className='navbar-nav mb-2 mb-lg-0 justify-content-end fs-5'>
//             {navbarLinks.map((link, index) => {
//               const { name, url } = link
//               return (
//                 <li className='nav-item' key={index}>
//                   <Link to={url} className='nav-link'>
//                     {name}
//                   </Link>
//                 </li>
//               )
//             })}
//             {/* {isLoggedin ? <UserLogin /> : <UserSignin />} */}
//             {userLoginState.userLogState ? <UserLogin /> : <UserSignin />}
//           </ul>

//           {/* NAV BAR SEARCH  */}
//           {/* <NavbarSearch></NavbarSearch> */}
//         </div>

//         <div className='ms-auto'>
//           <select onChange={changeLanguage} className='form-select' style={{ width: '120px' }}>
//             <option value='en'>English</option>
//             <option value='hi'>हिन्दी</option>
//             <option value='mr'>मराठी</option>
//           </select>
//         </div>

//       </div>
//     </nav>
//   )
// }

// export default Navbar
