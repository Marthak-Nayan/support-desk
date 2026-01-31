import React from 'react'

const Header = () => {
  return (
    <div>
       <header className="h-16 bg-white shadow-sm flex items-center justify-end px-6">
          <button
            className="px-3 py-2 text-sm rounded-lg border hover:bg-gray-100"
            onClick={logoutAction}
          >
            Logout
          </button>
        </header>
    </div>
  )
}

export default Header
